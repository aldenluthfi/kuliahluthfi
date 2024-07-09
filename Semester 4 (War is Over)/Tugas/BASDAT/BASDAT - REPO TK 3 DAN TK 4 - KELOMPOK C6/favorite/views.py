from django.shortcuts import render, redirect
from django.views.decorators.http import require_http_methods
from django.utils.timezone import now
from utils.db_utils import get_db_connection
from django.http import HttpResponseBadRequest
import datetime

def index(request):
    username = request.session.get('username')
    if not username:
        return redirect('authentication:login')

    favorites = get_favorites(username)
    available_shows = get_available_shows(username)
    context = {
        'favorites': favorites,
        'available_shows': available_shows
    }
    return render(request, 'favorite/index.html', context)

def parse_timestamp(input_timestamp):
    try:
        formatted_timestamp = input_timestamp.strip().replace(' p.m.', ' PM').replace(' a.m.', ' AM')
        dt = datetime.strptime(formatted_timestamp, "%B %d, %Y, %I:%M %p")
        formatted_sql_timestamp = dt.strftime('%Y-%m-%d %H:%M:%S')
        return formatted_sql_timestamp
    except Exception as e:
        raise

def delete_favorite(username, show_id):
    connection = get_db_connection()
    cursor = connection.cursor()
    
    try:
        sql_query = """
        DELETE FROM tayangan_memiliki_daftar_favorit
        WHERE username = %s AND id_tayangan = %s
        """
        cursor.execute(sql_query, (username, show_id))
        result = cursor.rowcount
        
        if result > 0:
            connection.commit()
        else:
            connection.rollback()
        return result
    except Exception as e:
        connection.rollback()
        raise e
    finally:
        cursor.close()
        connection.close()

def get_favorites(username):
    connection = get_db_connection()
    cursor = connection.cursor()
    cursor.execute(
        "SELECT TAYANGAN.id, TAYANGAN.judul, TAYANGAN_MEMILIKI_DAFTAR_FAVORIT.timestamp "
        "FROM TAYANGAN_MEMILIKI_DAFTAR_FAVORIT "
        "JOIN TAYANGAN ON TAYANGAN.id = TAYANGAN_MEMILIKI_DAFTAR_FAVORIT.id_tayangan "
        "WHERE TAYANGAN_MEMILIKI_DAFTAR_FAVORIT.username = %s",
        (username,)
    )
    rows = cursor.fetchall()
    cursor.close()
    connection.close()
    
    favorites = [{'show_id': row[0], 'judul': row[1], 'timestamp': row[2]} for row in rows]
    return favorites

def get_available_shows(username):
    connection = get_db_connection()
    cursor = connection.cursor()
    cursor.execute(
        "SELECT TAYANGAN.id, TAYANGAN.judul "
        "FROM TAYANGAN "
        "WHERE TAYANGAN.id NOT IN (SELECT id_tayangan FROM TAYANGAN_MEMILIKI_DAFTAR_FAVORIT WHERE username = %s)",
        (username,)
    )
    rows = cursor.fetchall()
    cursor.close()
    connection.close()

    shows = [{'id': row[0], 'judul': row[1]} for row in rows]
    return shows

def add_to_favorite(username, show_id):
    timestamp = now().replace(microsecond=0)
    connection = get_db_connection()
    cursor = connection.cursor()
    try:
        cursor.execute("SELECT judul FROM TAYANGAN WHERE id = %s", (show_id,))
        judul = cursor.fetchone()[0]

        cursor.execute(
            "INSERT INTO DAFTAR_FAVORIT (timestamp, username, judul) VALUES (%s, %s, %s)",
            (timestamp, username, judul)
        )
        cursor.execute(
            "INSERT INTO TAYANGAN_MEMILIKI_DAFTAR_FAVORIT (id_tayangan, username, timestamp) VALUES (%s, %s, %s)",
            (show_id, username, timestamp)
        )
        connection.commit()
    except Exception as e:
        connection.rollback()
        raise e
    finally:
        cursor.close()
        connection.close()

def add_to_daftar_favorit(username):
    connection = get_db_connection()
    cursor = connection.cursor()
    timestamp = now().replace(microsecond=0).isoformat()
    cursor.execute(
        "INSERT INTO DAFTAR_FAVORIT (timestamp, username, judul) VALUES (%s, %s, %s)",
        (timestamp, username, 'My Favorite Shows')
    )
    connection.commit()
    cursor.close()
    connection.close()
    return timestamp

@require_http_methods(['POST'])
def add_to_favorite_view(request):
    username = request.session.get('username')
    if not username:
        return redirect('authentication:login')

    show_id = request.POST.get('show_id')
    add_to_favorite(username, show_id)
    return redirect('favorite:index')

def delete_favorite_view(request):
    if request.method == 'POST':
        username = request.session.get('username')
        show_id = request.POST.get('show_id')

        if not username:
            return redirect('login')

        if not show_id:
            return HttpResponseBadRequest("Missing show_id parameter")

        try:
            deletion_result = delete_favorite(username, show_id)
            if deletion_result == 0:
                return HttpResponseBadRequest("No matching favorite found to delete.")
            return redirect('favorite:index')
        except Exception as e:
            return HttpResponseBadRequest(f"Error: {str(e)}")
    else:
        return HttpResponseBadRequest("Invalid request method.")

def favorite_details(request, show_id):
    if not request.session.get('username'):
        return redirect('authentication:login')

    username = request.session.get('username')
    connection = get_db_connection()
    cursor = connection.cursor()
    try:
        cursor.execute("""
            SELECT t.judul, tmf.timestamp 
            FROM TAYANGAN_MEMILIKI_DAFTAR_FAVORIT tmf
            JOIN TAYANGAN t ON tmf.id_tayangan = t.id
            WHERE tmf.username = %s AND tmf.id_tayangan = %s
        """, (username, str(show_id)))
        rows = cursor.fetchall()
        shows = [{'judul': row[0], 'timestamp': row[1]} for row in rows]
        show_title = shows[0]['judul'] if shows else 'Detail Daftar Favorit'
    except Exception as e:
        return HttpResponseBadRequest(f"Error processing request: {str(e)}")
    finally:
        cursor.close()
        connection.close()

    return render(request, 'favorite/detail.html', {'shows': shows, 'show_id': show_id, 'show_title': show_title})
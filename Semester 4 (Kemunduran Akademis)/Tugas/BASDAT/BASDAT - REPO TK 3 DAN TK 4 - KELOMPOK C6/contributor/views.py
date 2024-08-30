import psycopg2
from utils.db_utils import get_db_connection
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.contrib import messages
from django.views.decorators.http import require_http_methods

@require_http_methods(['GET', 'POST'])
def contributors(request):
    if request.session.get('username') is not None:
        try:
                connection = get_db_connection()

                cursor = connection.cursor()
                cursor.execute("""
                    SELECT *, 'Penulis Skenario' AS Peran FROM contributors JOIN PENULIS_SKENARIO ON contributors.id = PENULIS_SKENARIO.id
                    UNION
                    SELECT *, 'Sutradara' as Peran FROM contributors JOIN SUTRADARA ON contributors.id = SUTRADARA.id
                    UNION
                    SELECT *, 'Pemain' as Peran FROM contributors JOIN PEMAIN ON contributors.id = PEMAIN.id
                """)
                contribs = cursor.fetchall()

                context = [
                    {
                        "id": row[0],
                        "nama": row[1],
                        "jenis_kelamin": "Perempuan" if row[2] == 1 else "Laki-laki",
                        "kewarganegaraan": row[3],
                        "peran": row[5],
                    }
                for row in contribs]

                return render(request, 'contributor/contributors.html', {'contributors': context})

        except psycopg2.Error as e:
            print(e)
            return HttpResponse("Error occurred while connecting to the database")

        finally:
            if connection:
                cursor.close()
                connection.close()
    else:
        return redirect('authentication:index')
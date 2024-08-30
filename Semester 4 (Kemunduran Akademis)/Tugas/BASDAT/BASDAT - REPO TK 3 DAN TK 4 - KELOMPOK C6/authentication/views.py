import psycopg2
from utils.db_utils import get_db_connection
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.contrib import messages
from django.views.decorators.http import require_http_methods


@require_http_methods(['GET'])
def index(request):
    if request.session.get('username') is not None:
        return redirect('show:tayangan')
    return render(request, 'authentication/index.html')


@require_http_methods(['GET', 'POST'])
def login(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']

        try:
            connection = get_db_connection()

            cursor = connection.cursor()
            cursor.execute("SELECT * FROM pengguna WHERE username = %s AND password = %s", (username, password))
            user = cursor.fetchone()

            if user is not None:
                request.session['username'] = user[0]
                return redirect('show:tayangan')
            else:
                messages.error(request, 'Username or password is incorrect')
                return redirect('authentication:login')

        except psycopg2.Error as e:
            print(e)
            return HttpResponse("Error occurred while connecting to the database")

        finally:
            if connection:
                cursor.close()
                connection.close()

    else:
        return render(request, 'authentication/login.html')


@require_http_methods(['GET', 'POST'])
def register(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        negara_asal = request.POST['negara_asal']

        try:
            connection = get_db_connection()

            cursor = connection.cursor()
            cursor.execute("INSERT INTO pengguna (username, password, negara_asal) VALUES (%s, %s, %s)", (username, password, negara_asal))
            connection.commit()

            messages.success(request, 'Registration successful. Please login.')
            return redirect('authentication:login')

        except psycopg2.Error as e:
            if e.pgcode == 'P0001':
                messages.error(request, e.diag.message_primary)
                return redirect('authentication:register')
            else:
                print(e)
                return HttpResponse("Error occurred while connecting to the database")

        finally:
            if connection:
                cursor.close()
                connection.close()

    else:
        return render(request, 'authentication/register.html')


@require_http_methods(['GET'])
def logout(request):
    request.session.flush()
    return redirect('authentication:index')

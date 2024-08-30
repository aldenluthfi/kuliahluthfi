import psycopg2
from utils.db_utils import get_db_connection
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.contrib import messages
from django.views.decorators.http import require_http_methods
from datetime import datetime, timedelta

@require_http_methods(["GET", "POST"])
def subscriptions(request):
    if request.session.get('username') is not None:
        if request.method == 'GET':
            try:
                connection = get_db_connection()

                cursor = connection.cursor()
                cursor.execute(f"""
                    SELECT t.nama_paket, p.harga, p.resolusi_layar, devices, t.start_date_time, t.end_date_time
                    FROM TRANSACTION t JOIN PAKET p ON t.nama_paket = p.nama
                    JOIN (SELECT nama, array_agg(dukungan_perangkat) as devices FROM PAKET p JOIN DUKUNGAN_PERANGKAT d ON p.nama = d.nama_paket GROUP BY nama) d
                    ON p.nama = d.nama
                    WHERE t.username = '{request.session.get('username')}'
                    ORDER BY t.end_date_time DESC
                """)
                subscription = cursor.fetchall()

                user_subscription = [
                    {
                        "name": row[0],
                        "price": row[1],
                        "screen_resolution": row[2],
                        "device_support": ", ".join(row[3]),
                        "start_date": row[4].strftime("%d/%-m/%Y"),
                        "end_date": row[5].strftime("%d/%-m/%Y")
                    }
                    for row in subscription]

                cursor.execute(f"""
                    SELECT p.nama, p.harga, p.resolusi_layar, array_agg(dukungan_perangkat) as devices
                    FROM PAKET p JOIN DUKUNGAN_PERANGKAT d ON p.nama = d.nama_paket
                    GROUP BY p.nama, p.harga, p.resolusi_layar
                """)
                packages = cursor.fetchall()

                pricing = [
                    {
                        "name": row[0],
                        "price": row[1],
                        "screen_resolution": row[2],
                        "device_support": ", ".join(row[3]),
                    }
                    for row in packages]

                cursor.execute(f"""
                    SELECT array_agg(nama_paket), start_date_time, end_date_time, metode_pembayaran, timestamp_pembayaran, sum(harga) from TRANSACTION t JOIN PAKET p
                    ON t.nama_paket = p.nama
                    WHERE t.username = '{request.session.get('username')}'
                    GROUP BY start_date_time, end_date_time, metode_pembayaran, timestamp_pembayaran
                    ORDER BY timestamp_pembayaran DESC
                """)
                purchases = cursor.fetchall()

                transactions = [
                    {
                        "name": ', '.join(row[0]),
                        "start": row[1].strftime("%d/%-m/%Y"),
                        "end": row[2].strftime("%d/%-m/%Y"),
                        "method": row[3],
                        "timestamp": row[4].strftime("%d/%-m/%Y"),
                        "total": row[5]
                    }
                    for row in purchases]

                return render(request, 'subscription/subscriptions.html', {'user_subscription': user_subscription, 'pricing': pricing, "transactions": transactions})

            except psycopg2.Error as e:
                print(e)
                return HttpResponse("Error occurred while connecting to the database")

            finally:
                if connection:
                    cursor.close()
                    connection.close()
        else:
            return redirect('subscription:subscribe', request.POST['package'])
    else:
        return redirect('authentication:index')


@require_http_methods(["GET", "POST"])
def subscribe(request, paket):
    if request.session.get('username') is not None:
        try:
            connection = get_db_connection()
            cursor = connection.cursor()

            if request.method == 'POST':
                package_name = paket
                start_date = datetime.now()
                end_date = start_date + timedelta(days=30)
                payment_method = request.POST['payment_method']

                cursor.execute(f"""
                    INSERT INTO TRANSACTION (username, nama_paket, start_date_time, end_date_time, metode_pembayaran, timestamp_pembayaran)
                    VALUES ('{request.session.get('username')}', '{package_name}', '{start_date.strftime("%Y-%m-%d %H:%M:%S")}', '{end_date.strftime("%Y-%m-%d %H:%M:%S")}', '{payment_method}', '{datetime.now().strftime("%Y-%m-%d %H:%M:%S")}')
                """)
                connection.commit()

                messages.success(request, 'Subscription successful.')
                return redirect('subscription:subscription')

            else:
                cursor.execute(f"""
                            SELECT p.nama, p.harga, p.resolusi_layar, array_agg(dukungan_perangkat) as devices
                            FROM PAKET p JOIN DUKUNGAN_PERANGKAT d ON p.nama = d.nama_paket
                            WHERE p.nama = '{paket}'
                            GROUP BY p.nama, p.harga, p.resolusi_layar
                        """)
                packages = cursor.fetchall()

                pricing = [
                    {
                        "name": row[0],
                        "price": row[1],
                        "screen_resolution": row[2],
                        "device_support": ", ".join(row[3]),
                    }
                    for row in packages]
                return render(request, 'subscription/subscribe.html', {"pricing": pricing})

        except psycopg2.Error as e:
            print(e)
            return HttpResponse("Error occurred while connecting to the database")

        finally:
            if connection:
                cursor.close()
                connection.close()
    else:
        return redirect('authentication:index')

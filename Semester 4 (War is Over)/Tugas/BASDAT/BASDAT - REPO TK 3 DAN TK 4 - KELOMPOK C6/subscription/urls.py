from django.urls import path
from subscription.views import subscriptions, subscribe

app_name = 'subscription'

urlpatterns = [
    path('', subscriptions, name='subscription'),
    path('subscribe/<str:paket>', subscribe, name='subscribe')
]
from django.urls import path
from main.views import create_item_flutter, main_page, create_page, delete_salt

app_name = 'main'

urlpatterns = [
    path('', main_page, name='main_page'),
    path('create/', create_page, name='create'),
    path('delete/<str:hash>/', delete_salt, name='delete'),
    path('salts/<str:hash>/', main_page, name='salt_detail'),
    path('create-flutter/', create_item_flutter, name='create_item_flutter'),
]
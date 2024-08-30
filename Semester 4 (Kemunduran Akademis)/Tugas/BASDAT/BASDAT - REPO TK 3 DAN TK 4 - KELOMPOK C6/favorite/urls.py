from django.urls import path
from favorite.views import index, add_to_favorite_view, delete_favorite_view, favorite_details

app_name = 'favorite'

urlpatterns = [
    path('', index, name='index'),
    path('add/', add_to_favorite_view, name='add_to_favorite'),
    path('delete/', delete_favorite_view, name='delete_favorite'),
    path('details/<uuid:show_id>/', favorite_details, name='favorite_details'),
]
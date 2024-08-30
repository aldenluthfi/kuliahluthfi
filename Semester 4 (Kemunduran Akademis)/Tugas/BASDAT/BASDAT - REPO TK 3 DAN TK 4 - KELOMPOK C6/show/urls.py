from django.urls import path, re_path
from show import views

app_name = 'show'

urlpatterns = [
    path('trailer/', views.trailers, name='trailer'),
    path('tayangan/', views.show_tayangan, name='tayangan'),
    re_path(r'detail/(?P<id_tayangan>[0-9a-fA-F-]{36})/', views.detil_tayangan, name='detail'),  # UUID regex
    path('<str:judul>/episode/<str:sub_judul>/', views.episode_detail, name='episode'),
    path('save_review', views.save_review, name='save_review'),
    path('<str:judul>/update_review/', views.update_review, name='update_update'),
    path('add-to', views.add_to, name='add_to'),
    path('add-tontonan/<str:id>', views.add_tontonan, name='add_tontonan'),
    path('search', views.search, name='search'),

]

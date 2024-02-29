from django.urls import path
from debug.views import show_json, show_json_by_user, show_xml, show_json_by_hash, show_xml_by_hash

app_name = 'debug'

urlpatterns = [
    path('json/', show_json, name='json'),
    path('xml/', show_xml, name='xml'),
    path('json/<str:hash>/', show_json_by_hash, name='json_by_id'),
    path('xml/<str:hash>/', show_xml_by_hash, name='xml_by_id'),
    path('jsonx/<str:username>/', show_json_by_user, name='json_by_user'),
]
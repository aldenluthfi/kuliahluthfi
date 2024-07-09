from django.urls import path
from contributor.views import contributors

app_name = 'contributor'

urlpatterns = [
    path('', contributors, name='contributor'),
]
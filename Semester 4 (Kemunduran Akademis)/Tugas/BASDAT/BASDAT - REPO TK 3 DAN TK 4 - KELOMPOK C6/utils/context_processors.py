def username(request):
    return {'username': request.session.get('username', 'Guest')}
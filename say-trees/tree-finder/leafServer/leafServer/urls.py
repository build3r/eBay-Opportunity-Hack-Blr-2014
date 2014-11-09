from django.conf.urls import patterns, include, url
from django.conf.urls.static import static
from django.conf import settings
from django.contrib import admin

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'leafServer.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^filename_listener/?$', 'leaf.views.filename_listener'),
    url(r'^/?$', 'leaf.views.mainView'),
    url(r'^index.html/?$', 'leaf.views.mainView'),
) + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)

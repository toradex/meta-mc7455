SUMMARY = "GTK+ applet for NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gtk+3 libnotify libsecret networkmanager dbus-glib \
           gconf libgnome-keyring iso-codes nss \
           intltool-native \
"

inherit gnomebase gsettings gtk-icon-cache gobject-introspection gettext

GNOME_COMPRESS_TYPE = "xz"

SRC_URI[archive.md5sum] = "e0373f4c0d0637716e6e385799a9080f"
SRC_URI[archive.sha256sum] = "ebef1c1050ff6d94cad060e8d259f2a88ae159cf83ca75cb71d9f76867877eed"

PACKAGECONFIG[modemmanager] = "--with-wwan,--without-wwan,modemmanager"
PACKAGECONFIG[broadband-provider-info] = "--enable-mobile-broadband-provider-info,--disable-mobile-broadband-provider-info"
PACKAGECONFIG[selinux] = "--with-selinux,--without-selinux"
PACKAGECONFIG ??= ""

GI_DATA_ENABLED_libc-musl = "False"

do_compile_prepend() {
    export GIR_EXTRA_LIBS_PATH="${B}/src/libnma/.libs"
}

do_configure_append() {
    # Sigh... --enable-compile-warnings=no doesn't actually turn off -Werror
    for i in $(find ${B} -name "Makefile") ; do
        sed -i -e s%-Werror[^[:space:]]*%%g $i
    done
}

RDEPENDS_${PN} =+ "networkmanager"

FILES_${PN} += " \
    ${datadir}/nm-applet/ \
    ${datadir}/libnma/wifi.ui \
    ${datadir}/metainfo \
"

# musl builds generate gir files which otherwise go un-packaged
FILES_${PN}-dev += " \
    ${datadir}/gir-1.0 \
"

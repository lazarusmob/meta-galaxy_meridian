SUMMARY = "Common files for IrDA"
DESCRIPTION = "Provides common files needed to use IrDA. \
IrDA allows communication over Infrared with other devices \
such as phones and laptops."
HOMEPAGE = "http://irda.sourceforge.net/"
BUGTRACKER = "irda-users@lists.sourceforge.net"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://irdadump/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://smcinit/COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://man/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://irdadump/irdadump.c;beginline=1;endline=24;md5=d78b9dce3cd78c2220250c9c7a2be178"

SRC_URI = "${SOURCEFORGE_MIRROR}/irda/irda-utils-${PV}.tar.gz \
          file://init"

SRC_URI[md5sum] = "84dc12aa4c3f61fccb8d8919bf4079bb"
SRC_URI[sha256sum] = "61980551e46b2eaa9e17ad31cbc1a638074611fc33bff34163d10c7a67a9fdc6"

export SYS_INCLUDES="-I${STAGING_INCDIR}"

inherit autotools-brokensep update-rc.d

INITSCRIPT_NAME = "irattach"
INITSCRIPT_PARAMS = "defaults 20"

do_compile () {
    oe_runmake -e -C irattach
    oe_runmake -e -C irdaping
}

do_install () {
	install -d ${D}${sbindir}
	oe_runmake -C irattach ROOT="${D}" install
	oe_runmake -C irdaping ROOT="${D}" install

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

SUMMARY = "Initial Java 1.4-compatible (and not higher) compiler"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
DEPENDS = "jikes-native classpath-initial"

S = "${WORKDIR}"

inherit native

do_configure() {
 :
}

do_compile() {
  echo "#!/bin/sh" > jikes-initial
  echo "${STAGING_BINDIR_NATIVE}/jikes -bootclasspath ${STAGING_DATADIR_NATIVE}/classpath-initial/glibj.zip \$@" >> jikes-initial
}

do_install() {
  install -d ${D}${bindir}
  install -m 0755 jikes-initial ${D}${bindir}
}

NATIVE_INSTALL_WORKS = "1"

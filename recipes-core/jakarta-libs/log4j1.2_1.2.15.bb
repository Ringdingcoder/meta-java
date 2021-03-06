DESCRIPTION = "Java library to help the programmer output log statements to a variety of output targets"
AUTHOR = "Apache Software Foundation"
LICENSE = "AL2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f4ce41a6d1028498fcacde12f589bce7"

SRC_URI = "http://archive.apache.org/dist/logging/log4j/${PV}/apache-log4j-${PV}.tar.gz"

inherit java-library

S = "${WORKDIR}/apache-log4j-${PV}"

DEPENDS = "gnumail gnujaf"
DEPENDS_virtclass-native = "gnumail-native gnujaf-native"


JARFILENAME = "log4j-${PV}.jar"
ALTJARFILENAMES = "log4j-1.2.jar log4j1.2.jar"

do_compile() {
  mkdir -p build

  oe_makeclasspath cp -s activation gnumail

  # Built everything but the JMS and JMX classes (like in Debian)
	javac -sourcepath src/main/java -cp $cp -d build `find src/main/java -name "*.java" -and -not \( -iwholename "*jms*" -or -iwholename "*jmx*" \)`

  cp -R src/main/resources/* build/

  fastjar -C build -c -f ${JARFILENAME} .
}

SRC_URI[md5sum] = "10f04abe4d68d5a89e8eb167e4e45e1a"
SRC_URI[sha256sum] = "f5d9f6aa78b9156ae2de2a32d0f26507d2e73db4993d501db2e79f0bd803ab31"

NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native"


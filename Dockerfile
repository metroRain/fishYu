Pull base image
# ---------------
FROM centos:6

# Maintainer
# ----------
MAINTAINER Chang Su <netty4615@gmail.com>

# Define variable
ENV JAVA_ARCHIVE jdk-8u151-linux-x64.rpm

# Stage pineapple binary
COPY $JAVA_ARCHIVE /tmp/

# install java
WORKDIR /tmp
RUN chmod a+x $JAVA_ARCHIVE
RUN yum --assumeyes install $JAVA_ARCHIVE
RUN rm -rf $/tmp/JAVA_ARCHIVE
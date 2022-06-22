FROM java:8
MAINTAINER djobst "daniel.jobst@oth-regensburg.de"
RUN mkdir -p /opt/vs/de/lmu/server
COPY $VERSION/* /opt/vs/     # set by docker build --build-arg VERSION=normal|remote|callback
WORKDIR /opt/vs
EXPOSE $REGISTRY_PORT     # see above: --build-arg REGISTRY_PORT=1099
CMD ["java", "de.lmu.server.FrueherkennungServer"]

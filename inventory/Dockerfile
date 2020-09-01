FROM openliberty/open-liberty:kernel-java11-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Tim Zöller" \
  org.opencontainers.image.vendor="Tim Zöller" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/javahippie/microprofile-feature-demo" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="Tim Zöller" \
  name="inventory" \
  version="$VERSION-$REVISION" \
  summary="The inventory application from my MicroProfile demonstration" \
  description="This image provides inventory information on example products"

COPY --chown=1001:0 \
    src/main/liberty/config \
    /config/

COPY --chown=1001:0 \
    target/inventory.war \
    /config/apps

RUN configure.sh

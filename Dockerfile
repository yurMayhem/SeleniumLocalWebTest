FROM maven:3.9.9-eclipse-temurin-17

# Install wget, curl, ca-certificates, and Google Chrome
RUN apt-get update && apt-get install -y --no-install-recommends \
    wget \
    curl \
    ca-certificates \
    gnupg \
    && wget -q -O /tmp/google-chrome.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt-get install -y --no-install-recommends /tmp/google-chrome.deb \
    && rm -f /tmp/google-chrome.deb \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Pre-fetch Maven dependencies for efficient layer caching
COPY pom.xml testng.xml ./
RUN mvn dependency:go-offline -B || true

# Copy project source code and assets
COPY webpage ./webpage
COPY src ./src

# Configure default command to execute Selenium tests
CMD ["mvn", "clean", "test"]

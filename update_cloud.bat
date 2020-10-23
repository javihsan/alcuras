SET PATH=C:\GCloudSDK\google-cloud-sdk\bin;C:\openjdk_1.8.0\bin;%PATH%;
mvn clean package -P prod
gcloud config set project alcurasweb
gcloud app deploy .\target\alcurasweb\WEB-INF\appengine-web.xml --version=r8-0-5 --no-stop-previous-version --no-promote


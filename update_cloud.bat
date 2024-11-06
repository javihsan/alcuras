SET PATH=C:\GCloudSDK\google-cloud-sdk\bin;C:\openjdk_1.8.0\bin;%PATH%;

gcloud config set project alcurasweb
mvn package appengine:deploy -P prod

gcloud beta app migrate-config datastore-indexes-xml-to-yaml src\main\webapp\WEB-INF\datastore-indexes.xml
mvn clean package appengine:deployIndex

gcloud app deploy .\target\alcurasweb\WEB-INF\cron.yaml

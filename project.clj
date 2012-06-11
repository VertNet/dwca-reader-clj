(defproject dwca-reader-clj "0.2.0-SNAPSHOT"
  :description "Clojure wrapper for the GBIF Darwin Core Archive Reader library."
  :source-path "src/clj"
  :resources-path "resources"
  :dev-resources-path "dev"
  :repositories {"conjars" "http://conjars.org/repo/"
                 "gbif" "http://repository.gbif.org/content/groups/gbif/"
                 "zip4j" "http://mvnrepository.com/artifact/"
                 "maven2" "http://repo2.maven.org/maven2"}
  :jvm-opts ["-XX:MaxPermSize=128M"
             "-XX:+UseConcMarkSweepGC"
             "-Xms1024M" "-Xmx1048M" "-server"]
  :plugins [[swank-clojure "1.4.0-SNAPSHOT"]
            [lein-clojars "0.9.0"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.gbif/dwca-reader "1.9.1-SNAPSHOT"]
                 [net.lingala.zip4j/zip4j "1.3.1"]
                 [com.google.guava/guava "12.0"]]
  :dev-dependencies [[midje "1.4.0"]])

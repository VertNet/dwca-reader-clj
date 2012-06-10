(defproject dwca-reader-clj "0.1.0-SNAPSHOT"
  :description "Clojure wrapper for the GBIF Darwin Core Archive Reader library."
  :source-path "src/clj"
  :repositories {"gbif" "http://repository.gbif.org/content/groups/gbif/"}
  :jvm-opts ["-XX:MaxPermSize=128M"
             "-XX:+UseConcMarkSweepGC"
             "-Xms1024M" "-Xmx1048M" "-server"]
  :plugins [[lein-midje "1.0.8"]
            [swank-clojure "1.4.0-SNAPSHOT"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.gbif/dwca-reader "1.9.1-SNAPSHOT"]])

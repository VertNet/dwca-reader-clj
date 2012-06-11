(ns dwca.core
  "This namespace provides a Clojure API to the GBIF dwca-reader library."
  (:require [clojure.java.io :as io])
  (:use [clojure.string :only (join split)])
  (:import [com.google.common.io Files]
           [java.io File]
           [java.lang.reflect Field]
           [java.net URL]
           [net.lingala.zip4j.core ZipFile]
           [net.lingala.zip4j.exception ZipException]
           [org.gbif.dwc.record DarwinCoreRecord]
           [org.gbif.dwc.text ArchiveFactory]
           [org.gbif.file DownloadUtil]))

(defprotocol IDarwinCoreRecord
  "Protocol for accessing DarwinCoreRecord fields."
  (fields [x])
  (field-keys [x])
  (field-vals [x]))

(defn field-val
  "Return the string value of the supplied record field."
  [^Field field ^DarwinCoreRecord rec]
  {:pre [(instance? Field field)
         (instance? DarwinCoreRecord rec)]}
  (.setAccessible field true)
  (let [val (.get field rec)]
    (cond val (.trim val))))

(extend-protocol IDarwinCoreRecord
  DarwinCoreRecord
  (fields
    [^DarwinCoreRecord x]
    {:pre [(instance? DarwinCoreRecord x)]}
    (zipmap (keys x) (vals x)))
  (field-keys
    [^DarwinCoreRecord x]
    {:pre [(instance? DarwinCoreRecord x)]}
    (let [fields (->> x .getClass .getDeclaredFields vec)
          super-fields (->> rec .getClass .getSuperclass .getDeclaredFields vec)]
      (vec (map #(.getName %) (concat fields (subvec super-fields 3))))))
  (field-vals
    [^DarwinCoreRecord x]
    {:pre [(instance? DarwinCoreRecord x)]}
    (let [fields (->> x .getClass .getDeclaredFields vec)
          super-fields (->> rec .getClass .getSuperclass .getDeclaredFields vec)]
      (vec (map #(field-val % x) (concat fields (subvec super-fields 3)))))))

(defn download
  "Downloads a Darwin Core Archive from the supplied URL to the supplied file."
  [url file]
  (DownloadUtil/download (URL. url) (File. file)))

(defn unzip
  "Unzips the supplied ZIP file into the supplied directory."
  [file dir]
  (let [zipfile (ZipFile. file)]
    (.extractAll zipfile dir)))

(defn get-records
  "Returns a sequence of DarwinCoreRecord objects from an archive directory."
  [dir]
  (let [archive (ArchiveFactory/openArchive (File. dir))]
    (iterator-seq (.iteratorDwc archive))))

(defn archive-name
  "Return archive name from supplied URL as defined by the IPT."
  [url]
  (str "dwca-" (nth (split url #"=") 1)))

(defn open
  "Open archive at supplied URL and return a sequence of DarwinCoreRecord objects."
  [url]
  (let [temp-dir (Files/createTempDir)
        temp-path (.getPath temp-dir)
        archive-name (archive-name url)
        zip-path (str temp-path "/" archive-name ".zip")
        archive-path (str temp-path "/" archive-name)]
    (def cake archive-path)
    (download url zip-path)
    (unzip zip-path archive-path)
    (get-records archive-path)))

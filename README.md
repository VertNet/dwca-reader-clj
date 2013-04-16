# About

This project provides a Clojure wrapper around the [GBIF Darwin Core Archive Reader](http://code.google.com/p/darwincore/wiki/DarwinCoreArchiveReader) library. It's [hosted on Clojars here](https://clojars.org/dwca-reader-clj), so it's easy to use with Leinigen and Maven.

# Usage

In your `project.clj` file:

```clojure
:dependencies [dwca-reader-clj "0.8.0-SNAPSHOT"]
```
Or in your POM file:

```xml
<dependency>
  <groupId>dwca-reader-clj</groupId>
  <artifactId>dwca-reader-clj</artifactId>
  <version>0.8.0-SNAPSHOT</version>
</dependency>
```

Now fire up your REPL!

```bash
$ cd ~/Dropbox/github/vertnet/dwca-reader-clj
$ lein repl
```

Download, unzip, and iterate through Darwin Core records in an archive:

```clojure
(use `dwca.core)
(let [url "http://vertnet.nhm.ku.edu:8080/ipt/archive.do?r=ttrs_mammals"]
  (for [record (open url)]
    (field-vals record)))
```

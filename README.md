Chasing pooling issue
=====================

> sbt run com.github.reikje.Test

* observe Timeout
* change to .withSessionPool.maxSize(100)
* run again
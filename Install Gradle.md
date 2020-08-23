## Instalação do Gradle

> **Não é preciso instalar o gradle para rodar o código.**

Gradle will help running, compiling, testing, assembling (and many more)
Gradle can only run on Java version 8 or higher. Depending on what dependencies you use, you may require newer java versions.

Go to [https://gradle.org/install/](https://gradle.org/install/) and download the latest release.
Also you can **download it from a package manager** like [Homebrew](http://brew.sh/) (OS X) or [SDKMAN!](http://sdkman.io/) (Linux).

### Instalação Manual

#### 1️⃣ Step 1: [Download](https://gradle.org/releases) the latest Gradle distribution

The current Gradle release is version 5.6.2, released on 05 Sep 2019. The distribution zip file comes in two flavors:

- [Binary-only](https://gradle.org/next-steps/?version=5.6.2&format=bin)
- [Complete](https://gradle.org/next-steps/?version=5.6.2&format=all), with docs and sources

If in doubt, choose the binary-only version and browse [docs](https://docs.gradle.org/current) and [sources](https://github.com/gradle/gradle) online.

Need to work with an older version? See the [releases page](https://gradle.org/releases).

#### 2️⃣ Step 2: Unpack the distribution

###### **Linux & MacOS users**

Unzip the distribution zip file in the directory of your choosing, e.g.:

```bash
$ mkdir /opt/gradle
$ unzip -d /opt/gradle gradle-5.6.2-bin.zip
$ ls /opt/gradle/gradle-5.6.2
LICENSE NOTICE bin getting-started.html init.d lib media
```

###### **Microsoft Windows users**

Create a new directory **`C:\Gradle`** with **File Explorer**.

Open a second **File Explorer** window and go to the directory where the Gradle distribution was downloaded. Double-click the ZIP archive to expose the content. Drag the content folder **`gradle-5.6.2`** to your newly created **`C:\Gradle`** folder.

Alternatively you can unpack the Gradle distribution ZIP into **`C:\Gradle`** using an archiver tool of your choice.

#### 3️⃣ Step 3: Configure your system environment

###### **Linux & MacOS users**

Configure your **`PATH`** environment variable to include the **`bin`** directory of the unzipped distribution, e.g.:

```bash
$ export PATH=$PATH:/opt/gradle/gradle-5.6.2/bin
```

###### **Microsoft Windows users**

In **File Explorer** right-click on the **`This PC`** (or **`Computer`**) icon, then click **`Properties`** -> **`Advanced System Settings`** -> **`Environmental Variables`**.

Under **`System Variables`** select **`Path`**, then click **`Edit`**. Add an entry for **`C:\Gradle\gradle-5.6.2\bin`**. Click OK to save.

#### 4️⃣ Step 4: Verify your installation

Open a console (or a Windows command prompt) and run **`gradle -v`** to run gradle and display the version, e.g.:

```bash
$ gradle -v
------------------------------------------------------------
Gradle 5.6.2
------------------------------------------------------------
```

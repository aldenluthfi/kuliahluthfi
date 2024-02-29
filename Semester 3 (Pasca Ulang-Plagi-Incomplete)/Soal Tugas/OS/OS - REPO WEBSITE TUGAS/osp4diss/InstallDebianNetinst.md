---
---

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](DebianISOImage.md)
[NEXT](DebianGuestExportOva4.md)

# Installing Debian NetInst (guest) on VirtualBox

<br id="idx00">
## Default Settings

* You should adjust these following according to your own belief and faith.
  * Hostname: cbkadal
    * Use your GitHub account as hostname.
      <span style="color:red; font-weight:bold; font-size:larger;">Do not use cbkadal</span>
  * Domain: none
  * Root/Pass: root/cbkadal
  * UserName/Account/Pass: Cicak Bin Kadal/cbkadal/cbkadal
  * Disk #1 (16 GB): 1024MB swap and "/" (root partition).
  * Disk #2 (32 GB): 1024MB swap and "/mnt/lfs/" (partition).
  * <span style="color:red; font-weight:bold; font-size:larger;">You 
    might want to enlarge the SWAP size</span>

<br id="idx01">
## Import an EMPTY VirtualBox Guest

* E.g DEB11-02.ova

### Select and Click Settings

<img src="pictures/OS21-038a.jpg"  width="960">

<br id="idx02">
### Storage: Controller: SATA

<img src="pictures/OS21-039.jpg"  width="960">

* Adds Optical Drive

<img src="pictures/OS21-040.jpg"  width="960">

<br id="idx03">
### Add Disk Image

<img src="pictures/OS21-041.jpg"  width="960">

<br id="idx04">
### Open an ISO Image

* Eg. debian-11.2.0-amd64-netinst.iso
  <span style="color:red; font-weight:bold; font-size:larger;">(Check if this is the latest version!)</span>

<img src="pictures/OS21-042.jpg"  width="960">

<br id="idx05">
### Eg. Choose: debian-11.2.0-amd64-netinst.iso

* <span style="color:red; font-weight:bold; font-size:larger;">(Check if this is the latest version!)</span>

<img src="pictures/OS21-043.jpg"  width="960">

<br id="idx06">
### Storage: Storage Devices: OK

<img src="pictures/OS21-044.jpg"  width="960">

<br id="idx07">
## Starting Guest **DEB11-02**

<img src="pictures/OS21-045.jpg"  width="960">

<br id="idx08">
### Virtual Box Starts

<img src="pictures/OS21-046.jpg"  width="960">

<br id="idx09">
### Graphical Install

<img src="pictures/OS21-047.jpg"  width="960">

<br id="idx10">
### Select A Language: English (or else)

<img src="pictures/OS21-048.jpg"  width="960">

<br id="idx11">
### Select Your Location: Other (or else)

<img src="pictures/OS21-049.jpg"  width="960">

<br id="idx12">
### Select Your Location: Asia (or else)

<img src="pictures/OS21-050.jpg"  width="960">

<br id="idx13">
### Select Your Location: Asia: Indonesia (or whereever)

<img src="pictures/OS21-051.jpg"  width="960">

<br id="idx14">
### Configure Locales: United States (en_US.UTF-8) (or else)

<img src="pictures/OS21-052.jpg"  width="960">

<br id="idx15">
### Configure The Keyboard: American English (or else)

<img src="pictures/OS21-053.jpg"  width="960">

<br id="idx16">
### Load Installer Components From CD

<img src="pictures/OS21-054.jpg"  width="960">

<br id="idx17">
### Configure The Network

* Hostname (e.g.): cbkadal
  * <span style="color:red; font-weight:bold; font-size:larger;">"cbkadal" is just an example!
    Use your own GitHub account as hostname.</span>
    * You are not Cicak BinKadal!  Do not use cbkadal as hostname

<img src="pictures/OS21-055.jpg"  width="960">

* Domain Name: none

<img src="pictures/OS21-056.jpg"  width="960">

<br id="idx18">
### <span style="color:red;">Troubleshooting: DNS Server Failure</span>

* Sometimes, the DNS Server does not set properly ([Check here](osp-117.md)).

<br id="idx19">
### Set Up Users And Passwords

* Root Password: cbkadal (or else)
  * <span style="color:red; font-weight:bold; font-size:larger;">"cbkadal" is just an example!</span>

<img src="pictures/OS21-057.jpg"  width="960">

* Full Name For The New User: Cicak BinKadal (e.g.)
  * <span style="color:red; font-weight:bold; font-size:larger;">"Cicak BinKadal" is just an example!</span>

<img src="pictures/OS21-058.jpg"  width="960">

* Username For Your Account: cbkadal (e.g.)
  * <span style="color:red; font-weight:bold; font-size:larger;">"cbkadal" is just an example! 
    Use your own GitHub account as username.</span>
    * You are not Cicak BinKadal! Do not use cbkadal.

<img src="pictures/OS21-059.jpg"  width="960">

* Choose A Password For The New User: cbkadal (e.g.)
  * <span style="color:red; font-weight:bold; font-size:larger;">"cbkadal" is just an example!</span>

<img src="pictures/OS21-060.jpg"  width="960">

<br id="idx20">
### Configure The Clock

* Select A City In Your Time Zone:  Western Indonesia (WIB)

<img src="pictures/OS21-061.jpg"  width="960">

<br id="idx21">
### Partitions Disk

<img src="pictures/OS21-062.jpg"  width="960">

* Partition method: Manual

<img src="pictures/OS21-063.jpg"  width="960">

<br id="idx22">
### Select: SCSI1 (0,0,0) (sda)

<img src="pictures/OS21-064.jpg"  width="960">

* Create A New Partition Table: Yes

<img src="pictures/OS21-065.jpg"  width="960">
<img src="pictures/OS21-066.jpg"  width="960">

* Create A New Partition

<img src="pictures/OS21-067.jpg"  width="960">

* Partition Size: 1024 MB
  * <span style="color:red; font-weight:bold; font-size:larger;">
    You might want to consider other SWAP sizes:
    </span>
    * 512MB
    * 1024MB
    * 2048MB

<img src="pictures/OS21-068.jpg"  width="960">

* Partition Type: Primary

<img src="pictures/OS21-069.jpg"  width="960">

* Location: Beginning

<img src="pictures/OS21-070.jpg"  width="960">

* **CLICK**: "Use as"

<img src="pictures/OS21-071.jpg"  width="960">

* Set Partition As A Swap Area

<img src="pictures/OS21-072.jpg"  width="960">

* Done: Swap Partition

<img src="pictures/OS21-073.jpg"  width="960">

* Second Partitiion

<img src="pictures/OS21-074.jpg"  width="960">

* Create A New Partition

<img src="pictures/OS21-075.jpg"  width="960">

* Partition Size: 16.2 GB

<img src="pictures/OS21-076.jpg"  width="960">

* Partition Type: Primary

<img src="pictures/OS21-077.jpg"  width="960">

* Done Setting Up The Partition

<img src="pictures/OS21-078.jpg"  width="960">

<br id="idx23">
### Select: SCSI2 (0,0,0) (sdb)

<img src="pictures/OS21-079.jpg"  width="960">

* Create A New Partition Table: Yes

<img src="pictures/OS21-080.jpg"  width="960">
<img src="pictures/OS21-081.jpg"  width="960">

* Create A New Partition

<img src="pictures/OS21-082.jpg"  width="960">

* Partition Size: 1024 MB
  * <span style="color:red; font-weight:bold; font-size:larger;">
    You might want to consider other SWAP sizes:
    </span>
    * 512MB
    * 1024MB
    * 2048MB

<img src="pictures/OS21-083.jpg"  width="960">

* Partition Type: Primary

<img src="pictures/OS21-084.jpg"  width="960">

* Location: Beginning

<img src="pictures/OS21-085.jpg"  width="960">

* **CLICK**: "Use as"

<img src="pictures/OS21-086.jpg"  width="960">

* Set Partition As A Swap Area

<img src="pictures/OS21-087.jpg"  width="960">

* Done: Swap Partition

<img src="pictures/OS21-088.jpg"  width="960">

* Second Partitiion

<img src="pictures/OS21-089.jpg"  width="960">

* Create A New Partition

<img src="pictures/OS21-090.jpg"  width="960">

* Partition Size: 33.3 GB

<img src="pictures/OS21-091.jpg"  width="960">

* Partition Type: Primary

<img src="pictures/OS21-092.jpg"  width="960">

* Click Mount Point /home

<img src="pictures/OS21-093.jpg"  width="960">

* Select: Enter Manually

<img src="pictures/OS21-094.jpg"  width="960">

* Mounting Point: Linux From Scratch (/mnt/lfs)

<img src="pictures/OS21-095.jpg"  width="960">

* Done Setting Up The Partition

<img src="pictures/OS21-096.jpg"  width="960">

* Finish Partitioning And Write Changes To Disk

<img src="pictures/OS21-097.jpg"  width="960">

* Write The Changes To Disk: Yes

<img src="pictures/OS21-098.jpg"  width="960">

<br id="idx24">
### Partition Disk Process

<img src="pictures/OS21-099.jpg"  width="960">

<br id="idx25">
### Installing The Base System

<img src="pictures/OS21-100.jpg"  width="960">

<br id="idx26">
### Configuring The Package Manager

* Scan Another CD or DVD: No

<img src="pictures/OS21-101.jpg"  width="960">

* Debian Archive Mirror Country: Indonesia

<img src="pictures/OS21-102.jpg"  width="960">

* Debian Archive Mirror: deb.debian.org

<img src="pictures/OS21-103.jpg"  width="960">

* HTTP proxy: none

<img src="pictures/OS21-104.jpg"  width="960">

* Configuring APT

<img src="pictures/OS21-105.jpg"  width="960">

<br id="idx27">
### Configuring Popularity-Contest: No (Up to you!)

<img src="pictures/OS21-106.jpg"  width="960">

<br id="idx28">
### Software Selection
* <span style="color:red; font-weight:bold; font-size:larger;">DO NOT SELECT</span> Debian Desktop Environment
* SELECT: SSH server
* SELECT: Standart System Utilities

<img src="pictures/OS21-107.jpg"  width="960">

<br id="idx29">
### Select And Install Software

<img src="pictures/OS21-108.jpg"  width="960">

<br id="idx30">
### Install The GRUB Boot Loader On A Harddisk

* Install the GRUB Boot Loader to the master boor record: YES

<img src="pictures/OS21-109.jpg"  width="960">

* Enter Device Manually: /dev/sda

<img src="pictures/OS21-110.jpg"  width="960">

* Installing GRUB Boot Loader

<img src="pictures/OS21-111.jpg"  width="960">

<br id="idx31">
### Installation Complete

<img src="pictures/OS21-112.jpg"  width="960">

<br id="idx32">
### Rebooting The System

<img src="pictures/OS21-113.jpg"  width="960">

<br id="idx33">
### GRUB Loader

<img src="pictures/OS21-114.jpg"  width="960">

<br id="idx34">
### Login Test

* Login Test as <span style="color:red; font-weight:bold; font-size:larger;">root</span>

<img src="pictures/OS21-115.jpg"  width="960">

* (root)

```
apt-get update
apt-get dist-upgrade -y;
poweroff
```

<img src="pictures/OS21-116.jpg"  width="960">

<br id="idx35">
# DONE

<br id="endofpage"><br>

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](DebianISOImage.md)
[NEXT](index.md)
<br>


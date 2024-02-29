---
---

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://os2xx.github.io/osp4diss/)
[GITHUB](https://github.com/os2xx/osp4diss/)
[TOP](#)
[BOTTOM](#endofpage)

# Table of Contents

* [Import/Rename/Export/Delete a Virtual Guest](#idx01)
* VirtualBox
  * [VirtualBox: Installing on Windows](https://doit.vlsm.org/012.html)
  * [VirtualBox: Debian Guest Preparation](https://doit.vlsm.org/013.html)
  * [VirtualBox: Debian Guest Installation](https://doit.vlsm.org/014.html)
  * [VirtualBox: How to compile Linux Kernel on a Debian Guest](https://doit.vlsm.org/007.html)
    * [VirtualBox: Linux/AMD64 Kernel Configuration File](https://doit.vlsm.org/008.html)
* UTM MacOS M1
  * [UTM MacOS M1: How to compile Linux Kernel on a Debian Guest](https://doit.vlsm.org/011.html)
    * [UTM MacOS M1: Linux/M1 Kernel Configuration File](https://doit.vlsm.org/010.html)
* [<span style="color:red;font-weight:bold;">Troubleshooting</span>](#idx02a)
* [Running a VirtualBox Debian Guest](#idx03)
* [Dress Up Your Virtual Guest](#idx04)
* [PULL from / PUSH to GitHub](#idx05)
* [GnuPG](#idx05a)
* [More Links](#idx06)
* [OLD AOS](AOS.md) --- [OLD ASP](ASP.md)

<hr>
[&#x213C;](#)<br id="idx01">
# Import/Export/Delete a Virtual Guest

* [EXPORTing OVA](DebianGuestExportOva.md) -- e.g. DEB11-00.ova
* [RENAMing OVA](DebianGuestExportOva1.md) -- e.g. rename DEB11-00 to DEB11-01
* [IMPORTing a VirtualBox Guest with a different Name](DebianGuestImportOva.md)
* [DELETING Debian VirtualBox Guest(s)](DebianGuestDeleteOva.md)

[&#x213C;](#)<br id="idx02a">
# <span style="color:red;font-weight:bold;">Troubleshooting</span>
* [DNS Failure](osp-117.md)
* [More On MacOS (Intel based)](https://fxdros.github.io/virtualbox-on-macos/)
* [Installing Debian Gnu/Linux On Apple MacBook Air M1 (UTM/qemu)](https://doit.vlsm.org/009.html)
* [Windows 10: VirtualBox tidak bisa Print Screen](https://rahmatm.samik-ibrahim.vlsm.org/2021/01/windows-10-virtualbox-tidak-bisa-print.html)
* [Windows 10: SFC and DISM mantras](https://rahmatm.samik-ibrahim.vlsm.org/2021/07/windows-10-sfc-and-dism-mantras.html)
* [Windows 10: Simple Troubleshooting Tools](https://rahmatm.samik-ibrahim.vlsm.org/2021/10/four-simple-windows-troubleshooting.html)
* [VirtualBox: SWAP File On Main Filesystem](osp-125.md)
* [VirtualBox: To Shrink and Clean the VDI Files](osp-126.md)
* [LFS Check List](osp-131.md)

[&#x213C;](#)<br id="idx03">
# Running a VirtualBox Debian Guest

* [Startup a VirtualBox Guest](osp-002-startup.md)
* [Login from a Console](osp-002-login.md)
* [Shutdown a VirtualBox Guest](osp-002-shutdown.md)
* [Login with SSH](osp-002-ssh.md)
* [SCP](osp-002-scp.md) --- Secure copying from host to guest and from guest to host.
* The ATM Way, GSGS and Read:
  * [Study some Command Lines, Editor, Regular Expression (regex), and String Processing](Welcome2GNULinux.md)
  * [From Students for Students 2021 - ...](osp-127.md)
  * [More links about Operating Systems](osp-115.md)
* [EXPORT](#idx01) your Virtual Guest to an OVA file. Estimated OVA size 355 MB.

[&#x213C;](#)<br id="idx04">
# Dress Up Your Virtual Guest

* [PASTE: passing “NEW LINE” or not?](osp-116.md)
* [DNS Server TEST](osp-118.md)
* [Update Your Debian Guest](osp-102.md)
* [Adding Debian Packages](osp-103.md)
* [Adding UserName](osp-104.md) -- E.g. "dummy" and "CicakBinKadal"
* [Rename Hostname](osp-105.md)
* [.bash_profile](osp-106.md)
* [.vimrc](osp-107.md)
* [.bash_aliases](osp-108.md)
* [SUPERUSER](osp-109.md)
* [Default Shell: BASH](osp-132.md)
* [RENAME](DebianGuestExportOva1.md) and [EXPORT](DebianGuestExportOva.md).
  E.g., "OSP232.ova".  Estimated OVA size 747 MB.

[&#x213C;](#)<br id="idx05">
# PULL from / PUSH to GitHub.com

* [SSH: Generating public/private rsa key pair](osp-110.md)
* [SSH: Put a Public Key at GitHub.com](osp-111.md)
* [GIT: .gitconfig](osp-112.md)
* [GIT: cloning from GitHub.com](osp-113.md)
* [mylog: Updating add commit push](osp-114.md)
* [The 4 GIT MANTRAS: (pull), add, commit, push](osp-119.md)
* [EXPORT](#idx01) your Virtual Guest to an OVA file. Estimated OVA size 645 MB.

[&#x213C;](#)<br id="idx05a">
# GnuPG

* [Generating a GnuPG KEY PAIR](W02-03.md)
  * Or import your GnuPG Key, if you have a valid one.
* [List of current GnuPG keys](W02-04.md)
* [Importing the Operating Systems public key](W02-05.md)
* [Signing the Operating Systems public key (Optional)](W02-06.md)
* [Export Public Key “mypubkey.txt” (os232)](W02-07.md)

[&#x213C;](#)<br id="idx06">
# More Links

* [C-lib: Long Options with getopt_long()](osp-122.md)
* [GitHub Page: A GitHub Page with no Jekyll Theme](https://doit.vlsm.org/001.html)
  * [GitHub Page: Template](https://template.vlsm.org/)
* [GNU autoconf example: A Small Hello World](osp-123.md)
* [GNUPG: How to symmetrically encrypt and decrypt a file](osp-121.md)
* [GNUPG: Kleopatra (Windows 10)](CBKadal3.md)
* [FUSE Demo and Links](osp-120.md)
* [LFS: Linux From Scratch --- Version 11.2](https://www.linuxfromscratch.org/lfs/view/11.2/)
  * [LFS: KernoTeX's Linux From Scratch 11.2 Highlights](osp-128.md)
  * [LFS: Check List](osp-131.md)
* [Linux: Making and encrypting a tarball](osp-001.md)
* [Linux: TOP (was Table Of Processes)](osp-101.md)
* [OS Log Codes](ETC/logCodes.txt)
* [OS/SP Public Key](ETC/rmspubkey.txt)
* [VirtualBox: How To compile Linux Kernel](https://doit.vlsm.org/007.html)
* [VirtualBox: How To Shrink and Clean VDI Files](https://lfs.vlsm.org/LFS-02-5.html)
* [VirtualBox: Installing Jekyll](https://doit.vlsm.org/005.html)
* [X11 for Windows 10](osp-003.md)

<br>
<hr>
[&#x213C;](#)<br id="endofpage"><br>

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://os2xx.github.io/osp4diss/)
[GITHUB](https://github.com/os2xx/osp4diss/)
[TOP](#)
[BOTTOM](#endofpage)
<br>


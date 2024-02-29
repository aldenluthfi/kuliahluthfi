---
---

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](index.md#idx02)
[NEXT](DebianGuestExportOva.html)

# Creating a NEW empty VirtualBox Guest

* You should adjust these following according to your own belief and faith.
  * Name: **DEB11-00** (<span style="color:red; font-weight:bold; font-size:larger;">your may choose a different guest name</span>).
  * Processors (Cores): 2 (check your CPU!)
  * Memory = 1024 MB (or more. 512MB is OK too!)
  * Storage1 = 16 GB (dynamically allocated)
  * Storage2 = 32 GB (dynamically allocated)
  * SSH: NAT  from host  (127.0.0.1 port 6022) to guest (10.0.2.15 port 22)
  * Jekyll: NAT from host (127.0.0.1 port 5000) to guest (10.0.2.15 port 4000)

<br>
## Click NEW

<img src="pictures/OS21-000.jpg"  width="960">

<br>
* Name = **DEB11-00**
  * Type: Linux
  * Version: Debian (64bit)

<img src="pictures/OS21-001.jpg"  width="960">

<br>
* Memory size = <span style="color:red; font-weight:bold; font-size:larger;">1024 MB</span>
  * Try to set for as much memory as possible. 
    Although it can be forced up to 512MB, it will be unreasonably slow.

<img src="pictures/OS21-002.jpg"  width="960">

<br>
* Create a Virtual Hard Disk

<img src="pictures/OS21-003.jpg"  width="960">

<br>
* Hard Disk type: VDI

<img src="pictures/OS21-004.jpg"  width="960">

<br>
* Storage: Dynamically Allocated

<img src="pictures/OS21-005.jpg"  width="960">

<br>
* Storage = 16 GB (dynamically allocated)

<img src="pictures/OS21-006.jpg"  width="960">

<br id="endofpage"><br>
## Created (and Done)

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](index.md#idx02)
[NEXT](DebianGuestExportOva.html)
<br>

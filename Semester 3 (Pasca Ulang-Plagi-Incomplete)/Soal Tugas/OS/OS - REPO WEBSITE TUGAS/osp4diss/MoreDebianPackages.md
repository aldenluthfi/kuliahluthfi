---
---

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](UpdateDebian.md)
[NEXT](Welcome2GNULinux.md)

# More Debian Packages

## SSH into the GUEST

* UserName: cbkadal (or whatever)

```
ssh -p 6022 cbkadal@localhost

```

<img src="pictures/H-OSP-08.jpg"  width="960">

```
# You need the "root" password
su -
# Update/Upgrade
apt-get update; apt-get dist-upgrade -y;

```
<br>
## The Package List 1

```
export DEBS="
apt-file
aptitude
autoconf
automake
bison
build-essential
gawk
git
git-flow
gnupg
gnupg-agent
liblocale-msgfmt-perl
locales-all
manpages-dev
net-tools
parted
sysstat
sysvbanner
texinfo
vim
x11-apps
"

```
<br>
## Installing Package List 1

```
apt-get install $DEBS -y

```


<br>
## The Package List 2

```
export DEBS="
apt-transport-https
bc
bridge-utils
ca-certificates
cgroupfs-mount
coreutils
curl
dns-root-data
dnsmasq-base
docker.io
fakeroot
flex
fuse 
libacl1-dev
libcap-dev
libelf-dev
libfuse-dev
libncurses-dev
libseccomp-dev
libselinux1-dev
libssl-dev
lynx
make
manpages
module-assistant
nvi
p7zip-full
pigz
pkg-config
runc
ssh
software-properties-common
rsync
sshfs
wget
"

```



<br>
## Installing Package List 2

```
apt-get install $DEBS -y

```

<br id="endofpage"><br>

[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](UpdateDebian.md)
[NEXT](Welcome2GNULinux.md)
<br>


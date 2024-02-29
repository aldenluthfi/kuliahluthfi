---
---
[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](index.md#idx07)
[NEXT](W02-01.md)

# Generating a GnuPG KEY PAIR

These following examples is for user **cbkadal** at **osp** (Guest).
Replace **cbkadal** with your own user name.

* RSA and RSA.
* Keysize is 4096 bits.
* key expires in 1 year.
* No passphrase
  * PUTTY: can not with no passphrase

```
gpg --full-generate-key

```

```
cbkadal@cbkadal:~$ gpg --full-generate-key

gpg (GnuPG) 2.2.27; Copyright (C) 2021 Free Software Foundation, Inc.
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

gpg: directory '/home/cbkadal/.gnupg' created
gpg: keybox '/home/cbkadal/.gnupg/pubring.kbx' created
Please select what kind of key you want:
   (1) RSA and RSA (default)
   (2) DSA and Elgamal
   (3) DSA (sign only)
   (4) RSA (sign only)
  (14) Existing key from card
Your selection? 1

RSA keys may be between 1024 and 4096 bits long.
What keysize do you want? (3072) 4096
Requested keysize is 4096 bits
Please specify how long the key should be valid.
         0 = key does not expire
      <n>  = key expires in n days
      <n>w = key expires in n weeks
      <n>m = key expires in n months
      <n>y = key expires in n years
Key is valid for? (0) 1y

Key expires at Sun 11 Sep 2022 12:58:35 WIB
Is this correct? (y/N) y

GnuPG needs to construct a user ID to identify your key.

Real name: Cicak Bin Kadal
Email address: cbkadal@localhost
Comment: CBK
You selected this USER-ID:
    "Cicak Bin Kadal (CBK) <cbkadal@localhost>"

Change (N)ame, (C)omment, (E)mail or (O)kay/(Q)uit? O

```

* No passphrase (**You decide!**).

<img src="pictures/Y2-00.jpg"  width="960">

* Confirm: No Passphrase

<img src="pictures/Y2-01.jpg"  width="960">

```
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilize the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilize the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.

gpg: /home/cbkadal/.gnupg/trustdb.gpg: trustdb created
gpg: key 60914D29C01C81F1 marked as ultimately trusted
gpg: directory '/home/cbkadal/.gnupg/openpgp-revocs.d' created
gpg: revocation certificate stored as 
     '/home/cbkadal/.gnupg/openpgp-revocs.d/CE17E9DB8AD01794E3BAE98B60914D29C01C81F1.rev'
public and secret key created and signed.

pub   rsa4096 2021-09-11 [SC] [expires: 2022-09-11]
      CE17E9DB8AD01794E3BAE98B60914D29C01C81F1
uid                      Cicak Bin Kadal (CBK) <cbkadal@localhost>
sub   rsa4096 2021-09-11 [E] [expires: 2022-09-11]

cbkadal@cbkadal:~$

```

<br id="endofpage"><br>
[HOME](index.md)
[ABOUT](README.md)
[WEB](https://osp4diss.vlsm.org/)
[GITHUB](https://github.com/os2xx/osp4diss)
[TOP](#)
[BOTTOM](#endofpage)
[PREV](index.md#idx07)
[NEXT](W02-01.md)


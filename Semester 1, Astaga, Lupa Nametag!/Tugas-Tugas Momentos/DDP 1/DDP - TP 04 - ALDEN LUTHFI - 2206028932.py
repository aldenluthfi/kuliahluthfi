from __future__ import annotations                                              # Import-import yang akan digunakan
from random import choice
from typing import Callable, Any
from tkinter import Button, Entry, Event, Frame, IntVar, Label, Tk, Toplevel
from tkinter.ttk import Combobox
import tkinter.messagebox as msg


class Restoran:

    def __init__(self) -> None:

        self.item_count: int = 0

        with open('menu.txt', 'r') as berkas:
            self.cek_menu(berkas.read())

    def cek_menu(self, isi: str) -> None:                                       # Memindai file menu

        slit: Callable[[str, str], list[str]] = lambda x, y: x.strip().split(y)
        entri: list[list[str]] = [slit(x, '\n') for x in slit(isi, '===') if x]
        
        self.full_menu: dict[str, list[list[str]]] = {}
        self.harga: dict[str, int] = {}

        for judul, *menu in entri:

            title: tuple[str, ...] = ('MEALS', 'DRINKS', 'SIDES')
            kategori: tuple[str, ...] = ('Kegurihan', 'Kemanisan', 'Keviralan')

            dict_tipe: dict[str, str] = {t: k for t, k in zip(title, kategori)} # Menentukan tipe kategori rating 
            tipe: str = dict_tipe[judul]

            self.full_menu[judul] = [['Kode', 'Nama', 'Harga', tipe, 'Jumlah']]

            for menu_item in menu:
                kode, nama, harga, rating = menu_item.split(';')

                isian: list[str] = [kode, nama, rp(int(harga)), rating]
                self.full_menu[judul].append(isian)                             # Representasi menu untuk jadi table

                self.harga.update(dict.fromkeys([kode, nama], int(harga)))      # Mengisi dictionary harga

                self.item_count += 1


class Meja:                                                                     # representasi sebuah meja

    def __init__(self, nomor: int, nama: str, pesanan: dict[str, int]) -> None:
        self.nomor: int = nomor
        self.nama: str = nama
        self.pesanan: dict[str, int] = pesanan


class GUI(Restoran, Tk):                                                        

    @staticmethod
    def button_styling(button: Button, text: str) -> None:                      # Stilisasi tombol agar sama
        button['text'] = text
        button['bg'] = 'blue'
        button['fg'] = 'white'
        button['borderwidth'] = 0
        button['width'] = 10

    @staticmethod
    def label_styling(label: Label, relief: str, text: str) -> None:            # Stylisasi label agar sama
        label['text'] = text
        label['borderwidth'] = 1
        label['relief'] = relief

    @staticmethod
    def spacing(container: Any, baris: int, kolom: int, *grid: int) -> None:    # Menentukan besar grid untuk tabel

        rows, cols = grid

        for row in range(rows):
            container.rowconfigure(row, minsize=baris, weight=1)

        for col in range(cols):
            container.columnconfigure(col, minsize=kolom, weight=1)

    @staticmethod
    def kotak(owner: Any, window: Any, item: str, *position: int) -> Combobox:  # Membuat kombobox pada tabel

        def func(event: Event[Any]) -> None:
            return owner.update_pesanan(item, event.widget.get())

        variable: IntVar = IntVar()
        
        kombo: Combobox = Combobox(window)

        kombo['textvariable'] = variable
        kombo['values'] = [_ for _ in range(10)]
        kombo['width'] = 3

        kombo.grid(row=position[0], column=position[1], sticky='NSEW')

        kombo.bind('<Return>', func)
        kombo.bind('<FocusOut>', func)
        kombo.bind('<<ComboboxSelected>>', func)

        return kombo

    @staticmethod
    def tombol(owner: Any, window: Any, meja: int, *position: int) -> Button:   # Membuat tombol meja-meja   

        x, y = position
        nomor: int = 2*x + y + 1

        button: Button = Button(window, borderwidth=0)
        button['text'] = str(nomor)
        button['command'] = lambda: owner.update_meja(nomor, 0)
        meja_terisi: Meja | None = master[nomor - 1]

        warna: str = 'gray'
        if nomor == meja:
            warna = 'blue'
        elif meja_terisi is not None:
            warna = 'red'

        button['bg'] = warna
        button.grid(row=x + 1, column=y, sticky='NSEW', pady=10, padx=20)

        return button

    @staticmethod
    def make_table(owner: Restoran, window: Any, order: dict[str, int]) -> None:# Membuat tabel
        baris: int = 1

        for judul in owner.full_menu:                                           # Menggunakan representasi menu
            label_judul: Label = Label(window, text=judul)
            label_judul.grid(row=baris, column=0, sticky='NSEW')
            baris += 1

            for i, entry in enumerate(owner.full_menu[judul]):

                if i > 0 and isinstance(owner, BuatPesanan):                    # Untuk tabel ber-kombobox
                    box: Combobox = GUI.kotak(owner, window, entry[0], baris, 4)
                    box.set(order.get(entry[0], 0))

                elif i > 0 and isinstance(owner, Selesai):                      # Untuk tabel tanpa kombobox
                    amount_label: Label = Label(window)
                    amount: str = str(order.get(entry[0], 0))
                    GUI.label_styling(amount_label, 'sunken', amount)
                    amount_label.grid(row=baris, column=4, sticky='NSEW')

                for j, widget in enumerate(entry):                              # semua sisa kolomnya
                    label_entry: Label = Label(window)
                    GUI.label_styling(label_entry, 'sunken', widget)
                    label_entry.grid(row=baris, column=j, sticky='NSEW')

                baris += 1

    @staticmethod
    def make_buttons(owner: Restoran, root: Any, meja: int) -> None:            # Membuat masing masing tombol
        for row in range(5):
            for col in range(2):
                GUI.tombol(owner, root, meja, row, col)

    def clear(self) -> None:                                                    # Untuk mengosongkan window
        for frames in self.winfo_children():
            for widget in frames.winfo_children():
                widget.destroy()
            frames.destroy()

    def centered(self):                                                         # Untuk mengetengahkan window
        self.update_idletasks()

        width = self.winfo_width()
        height = self.winfo_height() + self.winfo_rooty() - self.winfo_y()
        x = self.winfo_screenwidth() // 2 - width // 2
        y = self.winfo_screenheight() // 2 - height // 2

        self.geometry(f'{width}x{height}+{x}+{y}')


class MenuUtama(GUI, Restoran, Tk):                                             # Class untuk menu utama

    def __init__(self) -> None:
        Tk.__init__(self)
        super().__init__()

        self.title('Kafe Daun-Daun Pacilkom v2.0 🌿')                           # Mengatur judul window
        self.main_menu()

    def main_menu(self) -> None:

        self.geometry('425x150')
        self.centered()                                                         # Mengetengahkan window

        self.frame_buat = Frame(self, height=150, width=500)                    # Mengatur frame
        self.frame_selesai = Frame(self, height=150, width=500)

        self.frame_buat.pack(expand=True, fill='both', pady=(37.5, 0))
        self.frame_selesai.pack(expand=True, fill='both', pady=(0, 37.5))
                                        
        self.buat: Button = Button(self.frame_buat)                             # Setting tombol
        GUI.button_styling(self.buat, 'Buat Pesanan')
        self.buat['command'] = self.buat_pesanan
        self.buat['width'] = 30

        self.selesai: Button = Button(self.frame_selesai)
        GUI.button_styling(self.selesai, 'Selesai Menggunakan Meja')
        self.selesai['command'] = self.selesai_makan
        self.selesai['width'] = 30

        self.buat.pack(expand=True)                                             # Menempatkan widget
        self.selesai.pack(expand=True)

    def buat_pesanan(self) -> None:                                             # Untuk membuat pesanan
        BuatPesanan()

    def selesai_makan(self) -> None:                                            # Untuk selesai makan
        Selesai()


class BuatPesanan(GUI, Restoran, Toplevel):

    def __init__(self) -> None:

        if all(x is not None for x in master):
            msg.showerror(message='Mohon maaf, meja penuh.')
        else:
            Toplevel.__init__(self)
            super().__init__()

            self.pesanan: dict[str, int] = {}                                       # Untuk menyimpan pesanan
            self.nomor = choice([i for i, m in enumerate(master, 1) if not m])  # Memilih meja random
            self.title('Kafe Daun-Daun Pacilkom v2.0 🌿')                           # Mengatur judul window
            self.entry_nama()

    def inisialisasi_widget(self):                                              # Fungsi untuk inisialisasi widget
        self.frame_content: Frame = Frame(self)
        self.frame_button: Frame = Frame(self)
        self.label1: Label = Label(self.frame_content)
        self.label2: Label = Label(self.frame_content)
        self.label3: Label = Label(self.frame_content)
        self.entry: Entry = Entry(self.frame_content)

        self.kembali: Button = Button(self.frame_button)
        self.lanjut: Button = Button(self.frame_button)
        self.ubah: Button = Button(self.frame_content)

    def entry_nama(self) -> None:                                               # Window untuk memasukkan nama

        self.clear()                                                            # Mengosongkan window
        self.geometry('370x150')
        self.inisialisasi_widget()                                              # Inisialisasi widget
        self.centered()                                                         # Mengetengahkan window

        self.frame_content['height'] = 100
        self.frame_content['width'] = 350
        GUI.spacing(self.frame_content, 100, 175, 1, 2)

        self.frame_button['height'] = 50
        self.frame_button['width'] = 350
        GUI.spacing(self.frame_button, 50, 175, 1, 2)

        self.label1['text'] = 'Siapa nama Anda?'                                # Menulis label
        self.entry['width'] = 15

        self.kembali['command'] = self.destroy
        GUI.button_styling(self.kembali, 'Kembali')

        self.lanjut['command'] = self.pesan
        GUI.button_styling(self.lanjut, 'Lanjut')

        self.frame_content.pack(expand=True, fill='both', padx=10)              # Menempatkan widget
        self.frame_button.pack(expand=True, fill='both', padx=10)

        self.label1.grid(row=0, column=0, sticky='e', padx=(0, 10))
        self.entry.grid(row=0, column=1, sticky='w', padx=(10, 0))

        self.kembali.grid(row=0, column=0, sticky='e', padx=(0, 20))
        self.lanjut.grid(row=0, column=1, sticky='w', padx=(20, 0))

    def pesan(self) -> None:

        self.nama = self.entry.get()

        self.clear()                                                            # Mengosongkan window
        self.geometry('820x600')
        self.inisialisasi_widget()                                              # Inisialisasi widget
        self.centered()                                                         # Mengetengahkan window

        self.frame_content['height'] = 450                                      # Setting frame
        self.frame_content['width'] = 800

        rows: int = 8 + self.item_count
        GUI.spacing(self.frame_content, 450 // rows, 800 // 5, rows, 5)         # Mengatur spacing buat table

        self.frame_button['height'] = 50
        self.frame_button['width'] = 800
        GUI.spacing(self.frame_button, 50, 400, 1, 2)                           # Mengatur spacing buat table

        GUI.make_table(self, self.frame_content, self.pesanan)                  # Membuat table

        self.label1['text'] = f'Nama pemesan: {self.nama}'                      # Menulis label
        self.label2['text'] = f'No Meja: {self.nomor}'
        self.label3['text'] = f'Total harga: {rp(self.total())}'

        self.ubah['command'] = lambda: self.ubah_meja(self.nomor)               # Mengatur kerja tombol
        GUI.button_styling(self.ubah, 'Ubah')

        self.kembali['command'] = self.destroy
        GUI.button_styling(self.kembali, 'Kembali')

        self.lanjut['command'] = self.selesai
        GUI.button_styling(self.lanjut, 'Lanjut')

        self.frame_content.pack(expand=True, fill='both', padx=10)              # Menempatkan widget
        self.frame_button.pack(expand=True, fill='both', padx=10)

        self.label1.grid(row=0, column=0, columnspan=3)
        self.label2.grid(row=0, column=3, sticky='e')
        self.label3.grid(row=rows, column=4, sticky='w')

        self.ubah.grid(row=0, column=4, sticky='w')
        self.kembali.grid(row=0, column=0, sticky='e', padx=(0, 20))
        self.lanjut.grid(row=0, column=1, sticky='w', padx=(20, 0))

    def ubah_meja(self, nomor_meja: int) -> None:

        self.clear()                                                            # Mengosongkan window
        self.geometry('420x500')
        self.inisialisasi_widget()                                              # Inisialisasi widget
        self.centered()                                                         # Mengetengahkan window

        self.frame_content['height'] = 450                                      # Setting frame
        self.frame_content['width'] = 400
        GUI.spacing(self.frame_content, 450 // 8, 200, 8, 2)

        GUI.make_buttons(self, self.frame_content, nomor_meja)

        self.frame_button['height'] = 50
        self.frame_button['width'] = 400
        GUI.spacing(self.frame_button, 50, 200, 1, 2)

        self.label1['text'] = f'Silahkan klik meja kosong yang diinginkan:'     # Menulis Label
        self.label2['text'] = f'Info'
        self.label2['font'] = ('', 20, 'bold')
        self.label3['text'] = f'Merah: terisi\nAbu-abu: kosong\nBiru: meja Anda'

        self.kembali['command'] = lambda: self.update_meja(nomor_meja, 2)       # Mengatur kerja tombol
        GUI.button_styling(self.kembali, 'Kembali')

        self.lanjut['command'] = lambda: self.update_meja(nomor_meja, 1)
        GUI.button_styling(self.lanjut, 'Lanjut')

        self.frame_content.pack(expand=True, fill='both', padx=10)              # Menempatkan widget
        self.frame_button.pack(expand=True, fill='both', padx=10)

        self.label1.grid(row=0, column=0, columnspan=2)
        self.label2.grid(row=6, column=0, columnspan=2)
        self.label3.grid(row=7, column=0, columnspan=2)

        self.kembali.grid(row=0, column=0, sticky='e', padx=(0, 20))
        self.lanjut.grid(row=0, column=1, sticky='w', padx=(20, 0))

    def update_pesanan(self, item: str, amount: str) -> None:                   # menambahkan item ke pesanan saat ini
        if amount.isnumeric():
            self.pesanan[item] = int(amount)
        self.label3['text'] = f'Total harga: {rp(self.total())}'

    def update_meja(self, new_val: int, valid: int) -> None:                    # Mengubah meja jika diinginkan
        if master[new_val - 1]:
            msg.showerror(message='Mohon maaf, meja digunakan.', parent=self)
        else:
            self.ubah_meja(new_val)
            
            if valid == 1:
                self.nomor = new_val
            
            if valid == 1 or valid == 2:
                self.pesan()

    def selesai(self) -> None:                                                  # Menyimpan state jika selesai
        master[self.nomor - 1] = Meja(self.nomor, self.nama, self.pesanan)
        self.destroy()

    def total(self) -> int:                                                     # Menghitung total
        return sum(self.harga[n] * self.pesanan[n] for n in self.pesanan)


class Selesai(GUI, Restoran, Toplevel):

    def __init__(self) -> None:
        Toplevel.__init__(self)
        super().__init__()

        self.title('Kafe Daun-Daun Pacilkom v2.0 🌿')                           # Mengatur judul window
        self.meja: Meja | None = None

        self.inisialisasi_widget()
        self.pilih_meja()                                                       # Memunculkan pilih meja

    def inisialisasi_widget(self):                                              # Fungsi untuk inisialisasi widget
        self.frame_content: Frame = Frame(self)
        self.frame_button: Frame = Frame(self)
        self.label1: Label = Label(self.frame_content)
        self.label2: Label = Label(self.frame_content)
        self.label3: Label = Label(self.frame_content)

        self.kembali: Button = Button(self.frame_button)
        self.lanjut: Button = Button(self.frame_button)

    def pilih_meja(self) -> None:

        self.clear()                                                            # Mengosongkan window
        self.geometry('420x500')
        self.inisialisasi_widget()                                              # Inisialisasi widget
        self.centered()                                                         # Mengetengahkan window

        GUI.button_styling(self.kembali, 'Kembali')

        self.frame_content['height'] = 450                                      # Setting frame
        self.frame_content['width'] = 400
        GUI.spacing(self.frame_content, 450 // 8, 200, 8, 2)

        GUI.make_buttons(self, self.frame_content, 0)

        self.frame_button['height'] = 50
        self.frame_button['width'] = 400
        GUI.spacing(self.frame_button, 50, 400, 1, 1)

        self.label1['text'] = f'Silahkan klik meja yang selesai digunakan:'     # Menulis label
        self.label2['text'] = f'Info'
        self.label2['font'] = ('', 20, 'bold')
        self.label3['text'] = f'Merah: terisi\nAbu-abu: kosong'

        self.kembali['command'] = self.destroy                                  # Mengatur kerja tombol
        GUI.button_styling(self.kembali, 'Kembali')

        self.frame_content.pack(expand=True, fill='both', padx=10)              # Menempatkan semua widget
        self.frame_button.pack(expand=True, fill='both', padx=10)

        self.label1.grid(row=0, column=0, columnspan=2)
        self.label2.grid(row=6, column=0, columnspan=2)
        self.label3.grid(row=7, column=0, columnspan=2)

        self.kembali.grid(row=0, column=0)

    def review(self) -> None:                                                   # Membuat tabel checkout

        if self.meja == None:                                                   # Jika meja kosong
            msg.showerror(message='Mohon maaf, meja kosong.', parent=self)
            return

        self.clear()                                                            # Mengosongkan window
        self.geometry('820x600')
        self.inisialisasi_widget()                                              # Inisialisasi widget
        self.centered()                                                         # Mengetengahkan window

        self.frame_content['height'] = 450                                      # Setting frame
        self.frame_content['width'] = 800

        rows: int = 8 + self.item_count
        GUI.spacing(self.frame_content, 450 // rows, 800 // 5, rows, 5)

        self.frame_button['height'] = 50
        self.frame_button['width'] = 800
        GUI.spacing(self.frame_button, 50, 400, 1, 2)

        GUI.make_table(self, self.frame_content, self.meja.pesanan)             # Membuat tabel

        self.label1['text'] = f'Nama pemesan: {self.meja.nama}'                 # Menulis label
        self.label2['text'] = f'No Meja: {self.meja.nomor}'
        self.label3['text'] = f'Total harga: {rp(self.total())}'

        self.kembali['command'] = self.pilih_meja                               # Mengatur kerja tombol
        GUI.button_styling(self.kembali, 'Kembali')
        self.kembali['width'] = 20

        self.lanjut['command'] = self.selesai
        GUI.button_styling(self.lanjut, 'Selesai Gunakan Meja')
        self.lanjut['width'] = 20

        self.frame_content.pack(expand=True, fill='both', padx=10)              # Menempatkan semua widget
        self.frame_button.pack(expand=True, fill='both', padx=10)

        self.label1.grid(row=0, column=0, columnspan=3)
        self.label2.grid(row=0, column=3, sticky='e')
        self.label3.grid(row=rows, column=4, sticky='e')

        self.kembali.grid(row=0, column=0, sticky='e', padx=(0, 20))
        self.lanjut.grid(row=0, column=1, sticky='w', padx=(20, 0))

    def update_meja(self, new_val: int, _: int) -> None:                        # Me-set meja yang dipilih
        if not master[new_val - 1]:
            msg.showerror(message='Mohon maaf, meja kosong.', parent=self)
        else:
            self.meja = master[new_val - 1]
            self.review()

    def selesai(self) -> None:                                                  # Mengosongkan meja jika sudah selesai
        assert self.meja != None

        master[self.meja.nomor - 1] = None
        self.pilih_meja()

    def total(self) -> int:                                                     # Menghitung total harga
        assert self.meja != None

        pesan = self.meja.pesanan
        return sum(self.harga[n] * pesan[n] for n in pesan)


if __name__ == "__main__":
    rp: Callable[[int], str] = lambda x: f'Rp{x:,}'.replace(',', '.')           # Pengubah notasi rupiah
    master: list[None | Meja] = [None for _ in range(10)]                       # List yang berisi semua meja di cafe
    MenuUtama().mainloop()                                                      # Mainloop window utama
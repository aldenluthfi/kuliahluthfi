import tkinter as tk
import tkinter.messagebox as msg

class MainMenu:                                                                 # gatau kenapa harus pake class but ok
    def __init__(self) -> None:
        root.title('Karung Ajaib')
        self.create_main()
    
    def create_main(self) -> None: #Function untuk membuat widget
        self.label = tk.Label(root, text='Selamat datang Dek Depe di Karung Ajaib. Silahkan pilih Menu yang tersedia')
        self.lihat = tk.Button(root, text="LIHAT DAFTAR KARUNG", command=MainMenu.lihat_karung)
        self.masukkan = tk.Button(root, text="MASUKKAN ITEM", command=MainMenu.masuk_karung)
        self.keluarkan = tk.Button(root, text="KELUARKAN", command=MainMenu.keluar_karung)
        self.exit = tk.Button(root, text="EXIT", command=root.destroy)

        self.place(self.label, self.lihat, self.masukkan, self.keluarkan, self.exit)

    @staticmethod
    def place(*widgets): #function untuk menaruh widget
        for widget in widgets:
            widget.pack()
        
    @staticmethod
    def lihat_karung() -> None: #fitur lihat karung
        LihatDalamKarung()
    
    @staticmethod
    def masuk_karung() -> None: #fitur masukkan ke karung
        MasukkanDalamKarung()
    
    @staticmethod
    def keluar_karung() -> None: #fitur keluarkan dari karung
        KeluarkanDariKarun()
        
class LihatDalamKarung:
    def __init__(self) -> None: #membuat window top level
        self.pop = tk.Toplevel()
        self.pop.title('Lihat Karung')
        self.create_lihat()
    
    def create_lihat(self): #Function untuk membuat widget dan menaruh widget
        items: list[tk.Label] = []
        items_str: list[str] = set_item
        
        self.title = tk.Label(self.pop, text='Daftar Karung Ajaib')
        self.nama = tk.Label(self.pop, text='Nama Item')

        for num, item in enumerate(sorted(list(items_str)), 1):
            items.append(tk.Label(self.pop, text=f'{num}. {item}'))

        self.exit = tk.Button(self.pop, text="EXIT", command=self.pop.destroy)

        MainMenu.place(self.title, self.nama, *items, self.exit)

class MasukkanDalamKarung:
    def __init__(self) -> None: #membuat window top level
        self.pop = tk.Toplevel()
        self.pop.title('Masukkan Item')
        self.create_masuk()
    
    def create_masuk(self) -> None: #Function untuk membuat widget dan menaruh widget
        self.title = tk.Label(self.pop, text='Input Masukkan Item')
        self.nama = tk.Label(self.pop, text='Nama Item')
        self.entry = tk.Entry(self.pop)
        self.submit = tk.Button(self.pop, text='Masukkan', command=self.masuk)

        self.title.grid(column=1, row=0)
        self.nama.grid(column=0, row=1)
        self.entry.grid(column=1, row=1)
        self.submit.grid(column=1, row=2)
    
    def masuk(self) -> None: #memasukkan item
        item = self.entry.get()

        if item in set_item: #mengecek apakah sudah ada
            msg.showerror(title='ItemHasFound', message=f'Item dengan nama {item} sudah ada di dalam KarungAjaib. Item {item} tidak bisa dimasukkan lagi.')
            return
        else:
            msg.showinfo(title='Berhasil!', message=f'Berhasil memasukkan item {item}')
            set_item.add(item)

class KeluarkanDariKarun:
    def __init__(self) -> None: #membuat window top level
        self.pop = tk.Toplevel()
        self.pop.title('Keluarkan Item')
        self.create_keluar()
    
    def create_keluar(self) -> None:  #Function untuk membuat widget dan menaruh widget
        self.title = tk.Label(self.pop, text='Input Keluarkan Item')
        self.nama = tk.Label(self.pop, text='Nama Item')
        self.entry = tk.Entry(self.pop)
        self.submit = tk.Button(self.pop, text='Ambil', command=self.keluar)

        self.title.grid(column=1, row=0)
        self.nama.grid(column=0, row=1)
        self.entry.grid(column=1, row=1)
        self.submit.grid(column=1, row=2)
    
    def keluar(self) -> None: #mengeluarkan item
        item = self.entry.get()

        if item not in set_item: #mengecek apakah ada
            msg.showerror(title='ItemNotFound', message=f'Item dengan nama {item} tidak ditemukan di dalam karung')
            return
        else:
            msg.showinfo(title='Berhasil!', message=f'Berhasil mengeluarkan item {item}')
            set_item.remove(item)

if __name__ =="__main__":
    root: tk = tk.Tk()
    set_item: set[str] = set()
    MainMenu()
    root.mainloop()
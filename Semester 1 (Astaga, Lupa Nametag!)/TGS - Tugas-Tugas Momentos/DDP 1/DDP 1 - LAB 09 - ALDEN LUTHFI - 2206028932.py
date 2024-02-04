from __future__ import annotations
from random import randint, choice

class Entity:                                                                   # Class Entity
    def __init__(self, name: str, hp: int, atk: int):
        self.__name: str = name                                                 # 3 atribut class entity
        self.__hp: int = hp
        self.__atk: int = atk
    
    def __str__(self):                                                          # Representasi string dari class
        return f'{self.name:20} HP:{self.hp}'
    
    @property                                                                   # Getter name
    def name(self) -> str:
        return self.__name

    @name.setter                                                                # Setter name
    def name(self, new_name: str) -> None:
        self.__name = new_name
    
    @property                                                                   # Getter HP
    def hp(self) -> int:
        return self.__hp

    @hp.setter                                                                  # Setter HP
    def hp(self, new_hp: int) -> None:
        self.__hp = new_hp
    
    @property                                                                   # Getter ATK
    def atk(self) -> int:
        return self.__atk

    @atk.setter                                                                 # Setter ATK
    def atk(self, new_atk: int) -> None:
        self.__atk = new_atk
    
    def attack(self, other: Player | Boss | Entity) -> str:                     # Function Attack
        other.take_damage(self.__atk)
        return f'{self.name} menyerang {other.name} dengan {self.atk} ATK!'
    
    def take_damage(self, damage: int) -> None:                                 # Function take damage
        self.__hp -= damage
    
    def is_alive(self) -> bool:                                                 # funtion is alive
        return self.__hp > 0

class Player(Entity):                                                           # Class Player
    def __init__(self, name: str, hp: int, atk: int, defense: int) -> None:
        self.__defense = defense                                                # Atribut baru yaitu defense
        super().__init__(name, hp, atk)
    
    def __str__(self):                                                          # Representasi string dari class
        return f'{self.name:20} HP:{self.hp}'
    
    @property                                                                   # Getter defense
    def defense(self) -> int:
        return self.__defense
    
    def take_damage(self, damage: int) -> None:                                 # funtion take damake yang berbeda
        if self.__defense < damage:
            self.hp -= (damage - self.__defense)
        else:
            pass

class Boss(Entity):                                                             # Class Boss
    def __init__(self, name: str, hp: int, atk: int) -> None:
        super().__init__(name, hp, atk)
            
    def __str__(self) -> str:                                                   # Representasi string dari class
        return f'{self.name:20} HP:{self.hp}'
    
    def attack(self, other: Player) -> str:                                     # Function attack yang berbeda
        other.take_damage(self.atk + other.defense)
        return f'{self.name} menyerang {other.name} dengan {self.atk} ATK!'

def main():
    nama_musuh: list[str] = ['Zombie', 'Skeleton', 'Spider']                    # Nama musush biasa yang mungkin
    bosses: list[str] = ['Giant Boss', 'Massive Boss']                       # Nama boss yang mungkin
    list_musuh: list[Boss | Entity]= []                                         # List musuh yang dirandomisasi

    atk_depram: int = int(input('Masukkan ATK Depram: '))                       # Input attack
    def_depram: int = int(input('Masukkan DEF Depram: '))                       # Input def
    depram: Player = Player("Depram", 100, atk_depram, def_depram)              # Inisialisasi depram

    jumlah_musuh: int = randint(1, 2)                                           # Randomisasi jumlah musuh
    print(f'Terdapat {jumlah_musuh} yang menghadang Depram!')
    print('-'*12)

    for _ in range(jumlah_musuh - 1):                                           # Membuat musuh-musuh
        nama: str = choice(nama_musuh)
        list_musuh.append(Entity(nama, randint(20, 100), randint(10, 30)))
    list_musuh.append(Boss(choice(bosses), randint(20, 100), randint(10, 30)))  # Menambahkan boss ke list musuh
    
    for e in list_musuh:                                                        # Pertarungan depram dengan setiap musuh
        print(f'{e.name} muncul!\n')
        print('---Status---')
        print(e)
        print(depram)
        print('-'*12)

        while e.is_alive() and depram.is_alive():                               # Saling serang sampai salah satu mati
            print(depram.attack(e))
            
            if not e.is_alive():
                break

            print(e.attack(depram))
        
        if not e.is_alive():
            print(f'{e.name} telah kalah!')
        elif not depram.is_alive():
            print('-'*12)
            print(f'\nTidak! Depram telah dikalahkan oleh musuhnya :(')
            break
        print('-'*12)
    
    if depram.is_alive():
        print('\nSelamat! Semua musuh Depram telah kalah!')

if __name__ == "__main__":                                                      # Fungsi main
    main()
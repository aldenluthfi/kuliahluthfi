import 'package:flutter/material.dart';
import 'package:gudang_garam/screens/all_salt_dj.dart';
import 'package:gudang_garam/screens/form.dart';
import 'package:gudang_garam/screens/menu.dart';
import 'package:gudang_garam/screens/salt_page.dart';
import 'package:gudang_garam/screens/user_salt_dj.dart';

class DrawerNavi extends StatelessWidget {
  const DrawerNavi({super.key});

  @override
  Widget build(BuildContext context) {
    return Drawer(
      backgroundColor: const Color.fromARGB(255, 172, 196, 11),
      child: ListView(
        children: [
          const SizedBox(
            height: 200,
            child: DrawerHeader(
              decoration: BoxDecoration(
              color: Color.fromARGB(255, 172, 196, 11),
              ),
              child: Column(
                children: [
                  Text(
                    'Gudang\nGaram',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 30,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                Padding(padding: EdgeInsets.all(10)),
                Text("Nyimpen apa-apa disini",
                  textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 15,
                      color: Colors.white,
                      fontWeight: FontWeight.normal,
                    ),
                  ),
                ],
              ),
            ),
          ),
          ListTile(
            tileColor: const Color.fromARGB(255, 172, 196, 11),
            leading: const Icon(
              Icons.home_outlined,
              color: Colors.white,
            ),
            title: const Text(
              'Halaman Utama',
              style: TextStyle(
                color: Colors.white
              ),
            ),
            onTap: () => Navigator.pushReplacement(
              context,
              MaterialPageRoute(
                builder: (context) => MyHomePage(),
              )
            )
          ),
          ListTile(
            tileColor: const Color.fromARGB(255, 172, 196, 11),
            leading: const Icon(
              Icons.add_box,
              color: Colors.white,
            ),
            title: const Text(
              'Tambah Item',
              style: TextStyle(
                color: Colors.white
              ),
            ),
            onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const AddForm()))
          ),
          ListTile(
            tileColor: const Color.fromARGB(255, 172, 196, 11),
            leading: const Icon(
              Icons.view_in_ar,
              color: Colors.white,
            ),
            title: const Text(
              'Lihat Item',
              style: TextStyle(
                color: Colors.white
              ),
            ),
            onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const SaltCard()))
          ),
          ListTile(
            tileColor: const Color.fromARGB(255, 172, 196, 11),
            leading: const Icon(
              Icons.view_in_ar,
              color: Colors.white,
            ),
            title: const Text(
              'Lihat Semua Item',
              style: TextStyle(
                color: Colors.white
              ),
            ),
            onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const ItemPage()))
          ),
          ListTile(
            tileColor: const Color.fromARGB(255, 172, 196, 11),
            leading: const Icon(
              Icons.view_in_ar,
              color: Colors.white,
            ),
            title: const Text(
              'Lihat Item User',
              style: TextStyle(
                color: Colors.white
              ),
            ),
            onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const UserItemPage()))
          ),
        ],
      ),
    );
  }
}
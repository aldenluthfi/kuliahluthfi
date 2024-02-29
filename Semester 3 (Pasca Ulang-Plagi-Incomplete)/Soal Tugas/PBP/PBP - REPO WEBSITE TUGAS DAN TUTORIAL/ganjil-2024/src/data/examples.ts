export const baseExample = `<!-- Anda bisa menggunakan ini sebagai dasar untuk bereksperimen/tugas -->
<!DOCTYPE html>
<html>
    <head>
        <title>Document</title>
    </head>
    <body>
        <p>Hello, World!</p>
    </body>
</html>`

export const paragraphExample = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUTPUT -->
<html>
    <body>
        <h2>Berikut ini adalah contoh paragraf</h2>
        <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut pharetra finibus porta. Fusce imperdiet
            turpis felis, ut elementum felis lobortis auctor. Curabitur finibus, nunc sed sagittis placerat, lacus
            massa venenatis lorem, malesuada rutrum ligula justo id lacus.
        </p>
        <p>
            Donec posuere ipsum faucibus lectus lacinia, vitae efficitur dolor vestibulum. Nulla fringilla gravida mollis.
            Aenean vel libero eleifend, luctus lacus ut, cursus mauris.
        </p>
    </body>
</html>
`

export const headingExample = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUTPUT -->
<html>
    <body>
        <h1>Saya adalah h1</h1>
        <h2>Saya adalah h2</h2>
        <h3>Saya adalah h3</h3>
        <h4>Saya adalah h4</h4>
        <h5>Saya adalah h5</h5>
        <h6>Saya adalah h6</h6>
    </body>
</html>
`

export const anchorExample1 = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUTPUT -->
<html>
    <body>
        <p>
            <a href="#">Halo, saya adalah link</a>
        </p>
        <p>
            <!-- Target _parent digunakan karena kode output di render dalam sebuah iframe -->
            <a href="https://google.com/" target="_parent">Link ini akan membuka Google di page yang sama</a>
        </p>
        <p>
            <!-- Target _blank akan membuka link pada page baru -->
            <a href="https://scele.cs.ui.ac.id/" target="_blank">Link ini akan membuka SCELE di tab baru</a>
        </p>
        <p>
            Link dapat juga diselipkan diantara teks <a href="#">seperti ini</a>
        </p>
    </body>
</html>
`

export const anchorExample2 = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUTPUT -->
<html>
    <body>
        <p>Clickable Button</p>
        <a href="https://google.com" target="_blank">
            <button>Button juga dapat diubah menjadi link</button>
        </a>
        <p>Clickable Image</p>
        <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">
            <img src="https://cs.ui.ac.id/wp-content/uploads/2021/06/logo-fasilkom-white-2.png" />
        </a>
    </body>
</html>
`

export const imgExample = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUTPUT -->
<html>
    <body>
        <h4>Ini adalah gambar</h4>
        <img src="https://via.placeholder.com/300x150" />

        <h4>Gambar ini tidak bisa di load</h4>
        <img src="lasflkasjf" alt="Unloadable Image" />

        <h4>Gambar ini memiliki custom width 250px (Original Size: 300x150)</h4>
        <img src="https://via.placeholder.com/300x150" width="250px" />
    </body>
</html>
`

export const buttonExample = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUTPUT -->
<html>
    <body>
        <h4>Button dengan sendirinya tidak melakukan apapun</h4>
        <button>Does Nothing</button>

        <h4>Button ini adalah link</h4>
        <a href="https://google.com/" target="_blank">
            <button>Open Google</button>
        </a>

        <h4>Button ini akan menampilkan sebuah popup menggunakan JavaScript</h4>
        <button id="click-me">Click Me</button>
        <script>
            // Line ini akan assign sebuah arrow function ke event onclick pada button.
            // () => alert("Clicked!") memiliki arti "buatlah fungsi
            // tak bernama yang membuat popup dengan tulisan 'Clicked!'
            document.getElementById("click-me").onclick = () => alert("Clicked!")
        </script>
    </body>
</html>
`

export const containerExample1 = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUPUT -->
<html>
    <body>
        <!-- Dengan div kita dapat mengaplikasikan sebuah CSS style ke sekelompok elemen -->
        <div style="background-color: lightgrey;">
            <h3>Artikel 1</h3>
            <p>Lorem ipsum yadda yadda this is the first article</p>
        </div>
        <!-- Kita juga dapat mengaplikasikan style positioning ke kelompok elemen ini untuk memisahkannya dari container sebelumnya -->
        <div style="background-color: grey; margin-top: 2rem">
            <h3>Artikel 2</h3>
            <p>Lorem ipsum yadda yadda this is the second article</p>
        </div>
    </body>
</html>
`

export const containerExample2 = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUPUT -->
<html>
    <body>
        <h4>Kelompok ini akan tersusun secara vertikal</h4>
        <div>
            <div style="background-color: lightgrey;">
                <h3>Artikel 1</h3>
                <p>Lorem ipsum yadda yadda this is the first article</p>
            </div>
            <div style="background-color: grey; margin-top: 2rem">
                <h3>Artikel 2</h3>
                <p>Lorem ipsum yadda yadda this is the second article</p>
            </div>
        </div>

        <!-- Berbagai macam recipe untuk layouting page akan dibahas di bagian Common Recipes -->
        <h4>Kelompok ini akan tersusun secara horizontal</h4>
        <div style="display: flex; align-items: center;">
            <div style="background-color: lightgrey;">
                <h3>Artikel 1</h3>
                <p>Lorem ipsum yadda yadda this is the first article</p>
            </div>
            <div style="background-color: grey; margin-left: 1rem;">
                <h3>Artikel 2</h3>
                <p>Lorem ipsum yadda yadda this is the second article</p>
            </div>
        </div>
    </body>
</html>
`

export const ajaxJQueryExample = `<!-- UBAH KODE INI UNTUK MELIHAT PERUBAHANNYA DI OUTPUT -->
<!DOCTYPE html>
<html>
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
    $(document).ready(function(){
      $("button").click(function(){
        $.get("https://api.thecatapi.com/v1/images/search", function(data) {
            $(".cat-photo").attr("src", data[0].url)
        });
      });
    });
    </script>
  </head>
    <body>
      <div>
      <img
          alt="cat"
          src="https://cdn2.thecatapi.com/images/0Vz3XgaPM.jpg"
          width="400"
          height="400"
          objectFit="cover"
          class="cat-photo"
      />
      </div>

      <button>Get a new cat!</button>

    </body>
</html>
`

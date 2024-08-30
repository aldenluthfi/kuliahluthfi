# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2206028932

### 1.2. Understanding how it works.
![image](https://github.com/aldenluthfi/adpro-tutorial10-timer/assets/83630284/0e9fc51e-aede-4d02-b451-f38f50b751a9)

The async program will execute the function in the background and will not block the main thread. The main thread will continue to execute the next code without waiting for the async function to finish. The async function will be executed in the background and will be executed in the order it was called. that is why "hey hey" appeared before "Howdy" and "Done". Dropping the spawner means that the program is done

### 1.3. Multiple Spawn and removing drop
![image](https://github.com/aldenluthfi/adpro-tutorial10-timer/assets/83630284/2d428714-4bc5-4141-9f4e-622f44ef5ede)

Not dropping the spawner will make the program think that it is not done. So it will be stuck in a waiting game and will not terminate. Multiple threads will be executed sequentially. this is similar like any procedural programming. of course, "hey hey" is outside the asycn implementation so will be executed first

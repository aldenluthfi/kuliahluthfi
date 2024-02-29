import React, { useState } from 'react';

const index = () => {
  const initialItems = [
    {
      title: 'Virtual Box',
      path: 'category/virtual-box',
      subCategories: [
        { title: 'Installing VirtualBox on Windows with Debian ISO', path: 'virtualbox/installing-vbox-on-windows' },
        { title: 'Debian Guest Preparation', path: 'virtualbox/debian-guest-preparation' },
        { title: 'Debian Guest Installation', path: 'virtualbox/debian-guest-installation' },
        { title: 'VirtualBox: Adding A 32 GB VirtualDisk', path: 'virtualbox/add-32-g-v-disk' },
        { title: 'VirtualBox: How to compile Linux Kernel on a Debian Guest', path: 'virtualbox/compile-linux-kernel' },
        { title: 'VirtualBox: Linux/AMD64 6.4 Kernel Configuration', path: 'virtualbox/linux-amd64-config' }
      ],
    },
    {
      title: 'UTM MacOS Silicon',
      path: 'category/utm-macos-silicon',
      subCategories: [
        { title: 'UTM MacOS Silicon: Installing Debian Gnu/Linux', path: 'utm/debian-installation-macos' },
        { title: 'UTM MacOS Silicon: Adding A 32 GB VirtualDisk', path: 'utm/add-32-g-v-disk-macos' },
        { title: 'UTM MacOS Silicon: How to compile Linux Kernel on a Debian Guest', path: 'utm/compile-linux-kernel-macos' },
        { title: 'UTM MacOS M1: Linux/ARM64 6.3 Kernel Configuration', path: 'utm/linux-arm64-config' },
      ],
    },
    {
      title: 'Debian Guest',
      path: 'category/debian-guest',
      subCategories: [
        { title: 'Windows Powershell and Terminal', path: 'debian-guest/windows-powershell-terminal' },
        { title: 'Start, Shutdown, Login, and Logout', path: 'debian-guest/start-shutdown' },
        { title: 'SCP', path: 'debian-guest/scp' },
        { title: 'Root (SuperUser)', path: 'debian-guest/root' },
        { title: 'Sources list', path: 'debian-guest/sources-list' },
        { title: 'Installing Debian Packages', path: 'debian-guest/debian-packages-1' },
        { title: 'Add User', path: 'debian-guest/adduser' },
        { title: 'Hostname', path: 'debian-guest/hostname' },
        { title: 'User .vimrc and .bash_aliases', path: 'debian-guest/vimrc-and-bashalias-user' },
        { title: 'Root .vimrc and .profile', path: 'debian-guest/vimrc-and-bashalias-root' },
        { title: 'ETC Linux', path: 'debian-guest/etc-linux' }
      ],
    },
    {
      title: 'Misc',
      path: 'category/misc',
      subCategories: [
        { title: 'Google Drive from MacOS and Windows', path: 'misc/google-drive-from-mac-and-windows' },
        { title: 'TBA', path: 'misc/tba' }
      ],
    }
  ];

  const [searchTerm, setSearchTerm] = useState('');
  const [items, setItems] = useState(initialItems);

  const handleSearch = (event) => {
    const value = event.target.value;
    setSearchTerm(value);

    const filteredItems = initialItems.filter((item) => {
      const titleMatch = item.title.toLowerCase().includes(value.toLowerCase());
      const subCategoryMatch = item.subCategories.some(
        (subItem) => subItem.title.toLowerCase().includes(value.toLowerCase())
      );
      return titleMatch || subCategoryMatch;
    });

    setItems(filteredItems);
  };

  const handleGoogleSearch = () => {
    if (searchTerm) {
      const searchQuery = encodeURIComponent(searchTerm);
      const searchURL = `https://www.google.com/search?q=${searchQuery}`;
      window.open(searchURL, '_blank');
    }
  };

  return (
    <div className='bg-[#081235] p-12'>
      <h2 className='text-4xl font-bold mb-4 text-white'>Table of Contents</h2>
      <div className='mb-4 my-8'>
        <input
          type='text'
          placeholder='Search...'
          value={searchTerm}
          onChange={handleSearch}
          className='px-4 py-2 text-lg rounded-md border border-blue-500 focus:outline-none focus:ring focus:border-blue-500 w-full'
        />
      </div>
      {items.length > 0 ? (
        <ul className='list-disc list-inside'>
          {items.map((item, index) => (
            <li key={index} className='mb-4'>
              <a
                href={`/doit-revamp/docs/${item.path}`}
                className='text-blue-500 hover:underline text-xl font-semibold'
              >
                {item.title}
              </a>
              {item.subCategories.length > 0 && (
                <ul className='ml-4'>
                  {item.subCategories.map((subItem, subIndex) => (
                    <li key={subIndex} className='mb-2'>
                      <a
                        href={`/doit-revamp/docs/${subItem.path}`}
                        className='text-blue-300 hover:underline text-lg'
                      >
                        {subItem.title}
                      </a>
                    </li>
                  ))}
                </ul>
              )}
            </li>
          ))}
        </ul>
      ) : (
        <div className='flex flex-col justify-center items-center'>
          <img
            src='https://pbs.twimg.com/media/DHK3g7dVYAAKmGP.jpg'
            alt='No results found'
            className='w-64 h-64 rounded'
          />
          <button
            onClick={handleGoogleSearch}
            className='mt-4 px-4 py-2 text-lg bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none font-semibold'
          >
            Try Searching on Google
          </button>
        </div>
      )}
    </div>
  );
};

export default index;

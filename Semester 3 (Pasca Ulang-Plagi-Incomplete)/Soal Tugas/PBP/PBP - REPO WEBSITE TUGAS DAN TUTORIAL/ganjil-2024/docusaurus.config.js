// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Platform-Based Programming',
  tagline: 'Odd Semester 2023/2024',
  favicon: 'img/logo.png',

  // Set the production url of your site here
  url: 'https://pbp-fasilkom-ui.github.io',
  // Set the /<baseUrl>/ pathname under which your site is served
  // For GitHub pages deployment, it is often '/<projectName>/'
  baseUrl: '/ganjil-2024/',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'pbp-fasilkom-ui', // Usually your GitHub org/user name.
  projectName: 'ganjil-2024', // Usually your repo name.
  trailingSlash: false,

  onBrokenLinks: 'warn',
  onBrokenMarkdownLinks: 'warn',

  // Even if you don't use internalization, you can use this field to set useful
  // metadata like html lang. For example, if your site is Chinese, you may want
  // to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: 'id',
    locales: ['id', 'en'],
    localeConfigs: {
      en: {
        htmlLang: 'en-AU',
      }
    }
  },
  customFields: {
    NODE_ENV: process.env.NODE_ENV
  },
  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          // Please change this to your repo.
          // Remove this to remove the "edit this page" links.
          // editUrl:
          //   'https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      // Replace with your project's social card
      image: 'img/logo.png',
      navbar: {
        title: 'PBP Ganjil 23/24',
        logo: {
          alt: 'PBP Ganjil 23/24',
          src: 'img/logo.png',
        },
        items: [
          {
            type: 'docSidebar',
            sidebarId: 'tutorialSidebar',
            position: 'left',
            label: 'Tutorial',
          },
          {
            to: '/assignments/',
            label: 'Tugas',
            position: 'left',
          },
          {
            to: 'awards',
            label: 'Penghargaan',
            position: 'right',
          },
          {
            to: 'playground',
            label: 'Playground',
            position: 'right',
          },
          {
            type: 'localeDropdown',
            position: 'right',
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'More',
            items: [
              {
                label: 'Playground',
                to: '/playground/',
              },
              {
                label: 'GitHub',
                href: 'https://github.com/pbp-fasilkom-ui/ganjil-2024/',
              },
            ],
          },
        ],
        copyright: `Copyright © PBP Fasilkom UI.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
      },
      colorMode: {
        defaultMode: 'light',
        disableSwitch: false,
        respectPrefersColorScheme: false,
      },
    }),
    plugins: [
      async function myPlugin(context, options) {
        return {
          name: "docusaurus-tailwindcss",
          configurePostCss(postcssOptions) {
            // Appends TailwindCSS and AutoPrefixer.
            postcssOptions.plugins.push(require("tailwindcss"));
            postcssOptions.plugins.push(require("autoprefixer"));
            return postcssOptions;
          },
        };
      },
      [
        '@docusaurus/plugin-content-docs',
        {
          id: 'assignments',
          path: 'assignments',
          routeBasePath: 'assignments',
          sidebarPath: require.resolve('./sidebars.js')
        },
      ],
    ],
};

module.exports = config;

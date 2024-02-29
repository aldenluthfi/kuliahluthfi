import React from 'react';
import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import HomepageFeatures from '@site/src/components/HomepageFeatures';

import styles from './index.module.css';

function HomepageHeader() {
    const { siteConfig } = useDocusaurusContext();
    return (
        <header className={clsx('hero hero--primary', styles.heroBanner)} style={{ height: "100%" }}>
            <div className="container">
                <h1 className="hero__title">{siteConfig.title}</h1>
                <p className="hero__subtitle">{siteConfig.tagline}</p>
                <div className={styles.buttons}>
                    <Link className="button button--secondary button--lg" style={{ marginRight: "0.5rem" }}
                        to="/docs">
                        Tutorial
                    </Link>
                    <Link
                        className="button button--secondary button--lg" style={{ marginLeft: "0.5rem" }}
                        to="/assignments">
                        Assignment
                    </Link>
                </div>
            </div>
        </header >
    );
}

export default function Home(): JSX.Element {
    return (
        <Layout
        title={"PBP Ganjil 2023/2024"}
            description="Situs Web Pemrograman Berbasis Platform Ganjil 2023/2024">
            <HomepageHeader />
            <main>
                <HomepageFeatures />
            </main>
        </Layout>
    );
}

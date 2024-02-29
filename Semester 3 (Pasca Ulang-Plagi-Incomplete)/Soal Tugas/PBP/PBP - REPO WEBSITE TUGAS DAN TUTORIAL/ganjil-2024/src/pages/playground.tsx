import React from 'react';
import Layout from '@theme/Layout'

import CodeEditor from '../components/CodeEditor';
import { baseExample } from '../data/examples';

const Playground: React.FC = () => {
  return (
    <Layout>
      <div style={{ padding: "3rem" }}>
        <h1 style={{ fontSize: "2.5rem" }}>HTML Playground</h1>
        <p>Feel free to experiment using this code editor.</p>
        <CodeEditor editorHeight='40vh' outputHeight='100vh' defaultValue={baseExample} />
      </div>
    </Layout>
  )
}

export default Playground;

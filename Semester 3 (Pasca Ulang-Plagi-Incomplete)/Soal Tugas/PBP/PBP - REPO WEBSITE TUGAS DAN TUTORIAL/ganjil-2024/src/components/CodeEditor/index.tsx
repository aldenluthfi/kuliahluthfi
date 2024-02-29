import React from "react"
import Editor from "@monaco-editor/react"

export interface CodeEditorProps {
    defaultValue: string
    editorHeight?: string
    outputHeight?: string
}

const CodeEditor: React.FC<CodeEditorProps> = ({ defaultValue, editorHeight = "40vh", outputHeight = "30vh" }) => {
    const [value, setValue] = React.useState(defaultValue)
    const outputRef = React.useRef(null)

    React.useEffect(() => {
        if (outputRef != null) {
            outputRef.current.src = "data:text/html;charset=utf-8," + encodeURIComponent(value);
        }
    }, [value])

    return (
        <div style={{ marginBottom: "1rem" }}>
            <div style={{ borderRadius: "0.5rem", overflow: "hidden" }}>
                <Editor
                    height={editorHeight}
                    defaultLanguage="html"
                    defaultValue={defaultValue}
                    theme="vs-dark"
                    onChange={(value) => setValue(value)}
                    loading="Loading Code Editor..."
                    options={{
                        minimap: {
                            enabled: false
                        }
                    }}
                />
            </div>
            <h4 style={{ marginTop: "1rem" }}>Output:</h4>
            <iframe style={{ width: "100%", minHeight: outputHeight, backgroundColor: "white", borderRadius: "0.5rem" }} ref={outputRef} />
        </div>
    )
}

export default CodeEditor

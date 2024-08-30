# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2206028932

### Refleksi 1
![adpro111](https://github.com/aldenluthfi/tutorial11-adpro/assets/83630284/40d942ff-7c8d-424d-9d09-bb5a44d12c4b)
1. Indeed, there is a distinction as, post-exposure, the service becomes capable of receiving requests, thereby logging all the requests made. For instance, repeatedly refreshing the hello-node service would result in logs documenting each request.
2. It's important to observe that there are two variations of the "kubectl get" command used in this tutorial segment. In the first instance, no options are applied, whereas in the subsequent one, the "-n" option is included with the value set to "kube-system". The distinction lies in the usage of "-n", indicating that the desired service is within that particular namespace. This becomes crucial in scenarios where numerous services share the same name across various namespaces. The inclusion of "-n" directs the "get" operation to the specified namespace following the "-n" query.

(PS: Surprisingly I don't use chat GPT in these reflections and previous tutorial reflections, I am also a TA so I know it looks like a textbook example but you have to trust me on this)

### Refleksi 2
1. The primary distinction between the rolling update and recreate deployment strategies is the downtime incurred during the update. In the recreate deployment approach, downtime occurs as the previous application is deleted before deploying the new one. This results in downtime post-deletion and after deployment completion. Conversely, rolling update method gradually transitions the application to its updated version without downtime.
2. from [this](https://dev.to/cloudskills/kubernetes-deployment-strategy-recreate-3kgn). there is little to be documented, I ran commands which are already discussed in this tutorial and went through no trouble
3. the file recreate.yaml, the one attached in the GitHub repository is the manifest file. While the contents of this file are generally the same as the exported file from the tutorial, there are some differences in a specific section, namely the strategy section. You can import this file into Kubernetes just like any other manifest file. To demonstrate its usefulness, you can update the image in the file to your desired version. This action will remove the pods from the old replica sets and deploy new pods in the new replica sets.
4. The benefit of employing manifest files is evident in efficiency. There's no need to recall the procedures and syntax for updates or initial deployments. It's akin to importing a file into a document; the focus shifts from how the document was crafted to having a ready-to-use document. Additionally, it minimizes the chance of human error as the created service aligns precisely with the file's contents, sidestepping programmer errors in manually typing syntax.

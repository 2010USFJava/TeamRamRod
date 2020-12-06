const MAX_FILE_SIZE = 10000000;

window.onload= function (){
console.log('loaded')
}
/* ENTER YOUR ENDPOINT HERE
   FILES UPLOADED TO MY ENDPOINT ARE AUTOMATICALLY DELETED EVERY FEW HOURS */

const API_ENDPOINT = 'https://heveuemg5m.execute-api.us-west-1.amazonaws.com/Prod'

new Vue({
  el: "#app",
  data: {
    file: '',
    uploadURL: ''
  },
  methods: {
    onFileChange (e) {
      let files = e.target.files || e.dataTransfer.files
      if (!files.length) return
      this.createFile(files[0])
    },
    createFile (file) {
      // var file = new File()
      let reader = new FileReader()
      reader.onload = (e) => {
        console.log('length: ', e.target.result.includes('data:file'))

        if (e.target.result.length > MAX_FILE_SIZE) {
          return alert('File is loo large - 10Mb maximum')
        }
        this.file = e.target.result
      }
      reader.readAsDataURL(file)
    },
    removeFile: function (e) {
      console.log('Remove clicked')
      this.file = ''
    },
    uploadFile: async function (e) {
      console.log('Upload clicked')
      // Get the presigned URL
      const response = await axios({
        method: 'GET',
        url: API_ENDPOINT
      })
      console.log('Response: ', response.data)
      console.log('Uploading: ', this.file)
      let binary = atob(this.file.split(',')[1])
      let array = []
      for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i))
      }
      let blobData = new Blob([new Uint8Array(array)], {type: ''})
      console.log('Uploading to: ', response.data.uploadURL)
      const result = await fetch(response.data.uploadURL, {
        method: 'PUT',
        body: blobData
      })
      console.log('Result: ', result)
      // Final URL for the user doesn't need the query string params
      this.uploadURL = response.data.uploadURL.split('?')[0]
    }
  }
})
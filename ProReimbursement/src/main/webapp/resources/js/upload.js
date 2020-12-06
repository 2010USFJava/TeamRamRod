const MAX_IMAGE_SIZE = 1000000;

window.onload= function (){
console.log('loaded')
}
/* ENTER YOUR ENDPOINT HERE
   FILES UPLOADED TO MY ENDPOINT ARE AUTOMATICALLY DELETED EVERY FEW HOURS */

const API_ENDPOINT = 'https://heveuemg5m.execute-api.us-west-1.amazonaws.com/Prod'

new Vue({
  el: "#app",
  data: {
    image: '',
    uploadURL: ''
  },
  methods: {
    onFileChange (e) {
      let files = e.target.files || e.dataTransfer.files
      if (!files.length) return
      this.createImage(files[0])
    },
    createImage (file) {
      // var image = new Image()
      let reader = new FileReader()
      reader.onload = (e) => {
        console.log('length: ', e.target.result.includes('data:image/jpeg'))
        if (!e.target.result.includes('data:image/jpeg')) {
          return alert('Wrong file type - JPG only.')
        }
        if (e.target.result.length > MAX_IMAGE_SIZE) {
          return alert('Image is loo large - 1Mb maximum')
        }
        this.image = e.target.result
      }
      reader.readAsDataURL(file)
    },
    removeImage: function (e) {
      console.log('Remove clicked')
      this.image = ''
    },
    uploadImage: async function (e) {
      console.log('Upload clicked')
      // Get the presigned URL
      const response = await axios({
        method: 'GET',
        url: API_ENDPOINT
      })
      console.log('Response: ', response.data)
      console.log('Uploading: ', this.image)
      let binary = atob(this.image.split(',')[1])
      let array = []
      for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i))
      }
      let blobData = new Blob([new Uint8Array(array)], {type: 'image/jpeg'})
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
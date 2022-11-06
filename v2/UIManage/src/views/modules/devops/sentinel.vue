<template>
  <div>
    <div class="title">sentinel</div>
    <div>
      <iframe class="sentinel" 
      src="http://192.168.56.20:8333/#/dashboard"></iframe>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {

    }
  },
  methods: {

  }
}
</script>

<style>
.title {
  padding: 10px 2px;
  font-size: 20px;
}
.sentinel{
  width: 80vw;
  height: 80vh;
}
</style>

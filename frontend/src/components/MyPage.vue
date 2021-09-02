<template>

<v-data-table
    :headers="headers"
    :items="myPage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'MyPage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "userId", value: "userId" },
            { text: "id", value: "id" },
            { text: "orderId", value: "orderId" },
            { text: "prodId", value: "prodId" },
            { text: "qty", value: "qty" },
            { text: "orderStatus", value: "orderStatus" },
            { text: "deliveryStatus", value: "deliveryStatus" },
            { text: "payStatus", value: "payStatus" },
            { text: "price", value: "price" },
            { text: "deliveryId", value: "deliveryId" },
            { text: "payId", value: "payId" },
        ],
        myPage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/mypages')

      this.myPage = temp.data._embedded.mypages;

    },
    methods: {
    }
  }
</script>


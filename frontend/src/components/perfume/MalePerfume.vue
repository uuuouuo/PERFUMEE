<template>
  <div id="app">
    <v-card color="blue" dark width="150px">
      <v-card-text class="black--text ms-4">{{ name }}</v-card-text>
      <v-card-text class="black--text ms-4">{{ brand }}</v-card-text>
      <v-img :src="image" height="200px" @click="selectPerfume"></v-img>
      <v-card-text>
        <v-row align="center" class="mx-0">
          <v-rating
            :value="rating"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          ></v-rating>

          <div class="black--text ms-4">{{ rating }}</div>
        </v-row>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    no: Number,
    brand: String,
    name: String,
    image: String,
    rating: Number,
  },
  methods: {
    selectPerfume() {
      this.$router.push("/detail/" + this.no);
    },
  },
};
</script>

<style></style>

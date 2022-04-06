<template>
  <v-card :loading="loading" class="mx-auto my-12" max-width="374">
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img height="250" :src="image"></v-img>

    <v-card-title>{{ name }}</v-card-title>

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

        <div class="grey--text ms-4">{{ rating }}</div>
      </v-row>

      <div class="my-4 text-subtitle-1">{{ brand }}</div>

      <div>
        {{ gender }}
      </div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-actions>
      <router-link :to="{ name: 'PerfumeDetail', params: { no: no } }">
        <v-btn color="deep-purple lighten-2" text @click="reserve">
          상품 보러가기
        </v-btn></router-link
      >
      <!-- <v-btn color="deep-purple lighten-2" text @click="reserve">
        상품 보러가기
      </v-btn> -->
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "item-list",
  props: {
    no: Number,
    name: String,
    brand: String,
    gender: String,
    image: String,
    rating: Number,
  },
  data: () => ({
    loading: false,
    selection: 1,
  }),
  methods: {
    reserve() {
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
    },
  },
};
</script>

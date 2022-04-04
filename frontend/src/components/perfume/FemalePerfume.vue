<template>
  <div id="app">
    <h2>여자향수추천</h2>
    <v-app>
      <v-container fluid grid-list-xl>
        <v-layout wrap justify-space-around>
          <v-flex v-for="image in images" :key="image.num">
            <v-card color="red" dark width="150px">
              <v-card-text>{{ image.num }}</v-card-text>
              <v-img :src="image.src" height="200px"></v-img>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-app>
  </div>
</template>

<script>
const PAGE_NUM = 10;

export default {
  data: () => ({
    images: Array.from(new Array(PAGE_NUM)).map((e, i) => ({
      num: i + 1,
      src: `https://picsum.photos/300/400?image=${i + 1}`,
    })),
  }),
};
</script>

<style></style>

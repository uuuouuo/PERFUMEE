import json

import pandas as pd
from django.views import View
from django.http import HttpResponse, JsonResponse
# Create your views here.

def index(request):
    return HttpResponse("안녕하세요 pybo에 오신것을 환영합니다.")

class Recomm(View):

    # def recommendation_perfume_by_perfume(self, request):
    #
    #     perfumes_name = request.data.get('perfumes_names')
    #     rank = 5
    #     perfumes_similarity = pd.read_pickle('C:\\recom_files\\perfumes_similarity.p')
    #     perfumes_similarity = perfumes_similarity.values
    #
    #     perfume_name_data = pd.read_pickle('C:\\recom_files\\perfume_data_reduction.p')
    #
    #     indecies = []
    #
    #     for perfume_name in perfumes_name:
    #         idx = perfume_name_data.loc[perfume_name_data['name'] == perfume_name]['perfume_id']
    #         indecies.append(idx)
    #
    #     similar_dict = {}
    #     for ps in perfumes_similarity[idx]:
    #         for i, p in enumerate(ps):
    #             similar_dict[i] = p
    #
    #     sorted_dict = sorted(similar_dict.items(), key=lambda item: item[1], reverse=True)
    #
    #     recom_perfumes = []
    #
    #     for per in sorted_dict[1:rank + 1]:
    #         idx = per[0]
    #         recom_perfumes.append(perfume_name_data.loc[idx])
    #
    #     return HttpResponse(recom_perfumes)

    def post(self, request):
        # a = request.POST.get
        request = json.loads(request.body)
        print(request)
        notes_name = request['notes_names']
        rank = 5
        perfume_note = pd.read_pickle('C:\\Users\\multicampus\\Desktop\\특화\\backend\\recomm\\perfume_recomm\\recom_files\\notes.p')
        # perfume_name_data = pd.read_pickle('C:\\Users\\multicampus\\Desktop\\특화\\backend\\recomm\\perfume_recomm\\recom_files\\perfume_data_reduction.p')

        perfume_rank = {}
        for i in range(0, len(perfume_note)):
            for note in notes_name:
                if perfume_note.loc[i][note.lower()] == 1:
                    if i in perfume_rank:
                        perfume_rank[i] = perfume_rank[i] + 1
                    else:
                        perfume_rank[i] = 1

        sorted_perfume_rank = sorted(perfume_rank.items(), key=lambda x: x[1], reverse=True)

        recom_perfumes = []

        for per in sorted_perfume_rank[1:rank + 1]:
            idx = per[0]+1
            # recom_perfumes.append(perfume_name_data.loc[idx])
            recom_perfumes.append(idx)
        # return HttpResponse(recom_perfumes)
        return HttpResponse(json.dumps({'perfumes': recom_perfumes}), content_type="application/json")
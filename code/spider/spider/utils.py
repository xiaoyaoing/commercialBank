import json

hash_penalty_name = {}
character = ['零', '一','二','三','四','五','六','七','八','九','十']
res = []
with open('ttt_1.txt', 'r', encoding='utf-8') as f:
    lines = f.readlines()
    for line in lines:
        d = eval(line)
        d['penalty_decision_money'] = d['penalty_decision_money'].replace(' ', '')
        d['penalty_decision_money'] = d['penalty_decision_money'].replace('罚款', '')
        if '万元' in d['penalty_decision_money']:
            idx = d['penalty_decision_money'].find('万元')
            d['penalty_decision_money'] = d['penalty_decision_money'][:(idx + 2)]
            d['penalty_decision_money'] = d['penalty_decision_money'].replace('万元', '0000')
        idx = 0
        while idx < len(d['penalty_decision_money']):
            if '0' <= d['penalty_decision_money'][idx] and d['penalty_decision_money'][idx] <= '9':
                break
            else:
                flag = False
                for item in character:
                    if d['penalty_decision_money'][idx] == item:
                        flag = True
                        break
                if flag:
                    break
            idx += 1
        d['penalty_decision_money'] = d['penalty_decision_money'][idx:]
        if len(d['penalty_decision_money']) > 0 and not ('0' <= d['penalty_decision_money'][0] and d['penalty_decision_money'][0] <= '9'):
            idx = 0
            s = 0
            sub = 0
            while idx < len(d['penalty_decision_money']) and not ('0' <= d['penalty_decision_money'][idx] and d['penalty_decision_money'][idx] <= '9'):
                if d['penalty_decision_money'][idx] == '百':
                   sub *= 100
                   s += sub
                   sub = 0
                elif  d['penalty_decision_money'][idx] == '十':
                   sub *= 10
                   s += sub
                   sub = 0
                else:
                    for i in range(len(character)):
                        if character[i] == d['penalty_decision_money'][idx]:
                            sub += i
                            break
                idx += 1
            s += sub
            d['penalty_decision_money'] = str(s) + d['penalty_decision_money'][idx:]
        idx = 0
        while idx < len(d['penalty_decision_money']):
            if '0' <= d['penalty_decision_money'][idx] and d['penalty_decision_money'][idx] <= '9':
                idx+= 1
                continue
            else:
                flag = False
                for item in character:
                    if d['penalty_decision_money'][idx] == item:
                        flag = True
                        break
                if flag:
                    continue
                else:
                    break
            idx += 1
        d['penalty_decision_money'] = d['penalty_decision_money'][:idx]
        if len(d['penalty_decision_money']) == 0:
            d['penalty_decision_money'] = 0
        elif not (d['penalty_decision_money'][0] >= '0' and d['penalty_decision_money'][0] <= '9'):
            d['penalty_decision_money'] = 0
        d['decisionNumber'] = d.pop('decision_number')
        d['punishedPartyName'] = d.pop('punished_party_name')
        d['agencyName'] = d.pop('punished_party_agency')
        d['agencyType'] = ''
        d['province'] = d.pop('punished_party_province')
        d['penaltyCause'] = d.pop('punished_cause')
        d['penaltyLaw'] = d.pop('penalty_basis_law')
        d['penaltyDecision'] = d.pop('penalty_decision')
        d['penaltyDate'] = d.pop('penalty_date')
        d['penaltyAmount'] = d.pop('penalty_decision_money')
        d['penaltyType'] = d.pop('agency_name')
        d['penaltyName'] = d.pop('penalty_name')
        for k in d:
            try:
                if len(d[k]) == 0:
                    d[k] = '-'
            except:
                continue
        res.append(d)

cat = ['公司', '分行', '支行', '银行', '店', '合作联社', '合作社', '服务中心', '厂', '行', '证券']
for line in res:
    for c in cat:
        if c in line['penaltyName']:
            line['agencyType'] = c
            break
print(len(res))
# with open('data_s.json', 'w', encoding='utf-8') as f:
#     json.dump(res, f, ensure_ascii=False)
#
# with open('latter_remain.txt', 'a+') as f:
#     for k in hash_penalty_name:
#         f.write(str(hash_penalty_name[k]) + '\n')
#
# hash_penalty_name = {}
#
# with open('latter.txt', 'r', encoding='gbk') as f:
#     lines = f.readlines()
#     for line in lines:
#         d = eval(line)
#         if len(d['agency_name']) == 0:
#             hash_penalty_name[d['penalty_name']] = d
#
# with open('latter_need_crawl.txt', 'a+') as f:
#     for k in hash_penalty_name:
#         f.write(str(hash_penalty_name[k]) + '\n')
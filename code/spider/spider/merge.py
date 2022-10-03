

mm = {}

res = []
with open('tot_result.txt', 'r', encoding='gbk') as f:
    lines = f.readlines()
    for line in lines:
        d = eval(line)
        if d['url'] in mm:
            continue
        else:
            mm[d['url']] = 1
            d['penalty_date'] = d['penalty_date'].replace('年', '-')
            d['penalty_date'] = d['penalty_date'].replace('月', '-')
            d['penalty_date'] = d['penalty_date'].replace('日', '-')
            res.append(d)

with open('latter.txt', 'r', encoding='gbk') as f:
    lines = f.readlines()
    for line in lines:
        d = eval(line)
        if d['url'] in mm:
            continue
        else:
            mm[d['url']] = 1
            d['penalty_date'] = d['penalty_date'].replace('年', '-')
            d['penalty_date'] = d['penalty_date'].replace('月', '-')
            d['penalty_date'] = d['penalty_date'].replace('日', '-')
            res.append(d)

with open('mid.txt', 'r', encoding='gbk') as f:
    lines = f.readlines()
    for line in lines:
        d = eval(line)
        if d['url'] in mm:
            continue
        else:
            mm[d['url']] = 1
            d['penalty_date'] = d['penalty_date'].replace('年', '-')
            d['penalty_date'] = d['penalty_date'].replace('月', '-')
            d['penalty_date'] = d['penalty_date'].replace('日', '-')
            res.append(d)

with open('prev.txt', 'r', encoding='gbk') as f:
    lines = f.readlines()
    for line in lines:
        d = eval(line)
        if d['url'] in mm:
            continue
        else:
            mm[d['url']] = 1
            d['penalty_date'] = d['penalty_date'].replace('年', '-')
            d['penalty_date'] = d['penalty_date'].replace('月', '-')
            d['penalty_date'] = d['penalty_date'].replace('日', '-')
            res.append(d)

with open('tot_prev.txt', 'r', encoding='gbk') as f:
    lines = f.readlines()
    for line in lines:
        d = eval(line)
        if d['url'] in mm:
            continue
        else:
            mm[d['url']] = 1
            d['penalty_date'] = d['penalty_date'].replace('年', '-')
            d['penalty_date'] = d['penalty_date'].replace('月', '-')
            d['penalty_date'] = d['penalty_date'].replace('日', '-')
            res.append(d)

with open('tot_mid.txt', 'r', encoding='gbk') as f:
    lines = f.readlines()
    for line in lines:
        d = eval(line)
        if d['url'] in mm:
            continue
        else:
            mm[d['url']] = 1
            d['penalty_date'] = d['penalty_date'].replace('年', '-')
            d['penalty_date'] = d['penalty_date'].replace('月', '-')
            d['penalty_date'] = d['penalty_date'].replace('日', '-')
            res.append(d)

with open('ttt_1.txt', 'a+', encoding='utf-8') as f:
    for line in res:
        f.write(str(line) + '\n')


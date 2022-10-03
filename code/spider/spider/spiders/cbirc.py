# -*- coding: utf-8 -*-
import json
from copy import deepcopy
import logging
import scrapy
import math
import re
from selenium.webdriver import ChromeOptions
from selenium.webdriver import Chrome


class CbircSpider(scrapy.Spider):
    name = 'cbirc'
    allowed_domains = ['www.cbirc.gov.cn']
    provinces = ['北京', '天津', '上海', '重庆', '河北', '山西', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西'
    , '山东', '河南', '湖北', '湖南', '广东', '海南', '四川', '贵州', '云南', '陕西', '甘肃', '青海', '台湾', '内蒙古', '广西,  西藏', '宁夏,  新疆', '香港',
                  '澳门', ]
    
    pro_nickname = {'京': '北京', '津':'天津' , '沪':'上海', '渝':'重庆', '冀':'河北', '晋':'山西',
    '辽':'辽宁', '吉':'吉林', '黑':'黑龙江', '苏':'江苏', '浙':'浙江', '皖':'安徽',
    '川':'四川', '闽':'福建', '赣':'江西', '鲁':'山东', '豫':'河南', '鄂':'湖北',
    '湘':'湖南', '粤':'广东', '琼':'海南', '黔':'贵州', '滇':'云南', '陕':'陕西',
    '甘':'甘肃', '青':'青海', '台':'台湾', '内蒙古':'内蒙古', '桂':'广西', '藏':'西藏', '宁':'宁夏', '新':'新疆','港':'香港', '澳':'澳门'
    }

    start_urls = ['http://www.cbirc.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4113&itemUrl=ItemListRightList.html&itemName=%E9%93%B6%E4%BF%9D%E7%9B%91%E4%BC%9A%E6%9C%BA%E5%85%B3&itemsubPId=931&itemsubPName=%E8%A1%8C%E6%94%BF%E5%A4%84%E7%BD%9A#1']

    # def __init__(self, name=None, **kwargs):
    #     super().__init__(name=None, **kwargs)
    #
    #     s1 = 'http://www.cbirc.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4113&itemUrl=ItemListRightList.html&itemName=%E9%93%B6%E4%BF%9D%E7%9B%91%E4%BC%9A%E6%9C%BA%E5%85%B3&itemsubPId=931&itemsubPName=%E8%A1%8C%E6%94%BF%E5%A4%84%E7%BD%9A#'
    #     s2 = 'http://www.cbirc.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4114&itemUrl=ItemListRightList.html&itemName=%E9%93%B6%E4%BF%9D%E7%9B%91%E5%B1%80%E6%9C%AC%E7%BA%A7&itemsubPId=931&itemsubPName=%E8%A1%8C%E6%94%BF%E5%A4%84%E7%BD%9A#'
    #     s3 = 'http://www.cbirc.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4115&itemUrl=ItemListRightList.html&itemName=%E9%93%B6%E4%BF%9D%E7%9B%91%E5%88%86%E5%B1%80%E6%9C%AC%E7%BA%A7&itemsubPId=931&itemsubPName=%E8%A1%8C%E6%94%BF%E5%A4%84%E7%BD%9A#'
    #
    #     # for i in range(1, 20):
    #     #     self.start_urls.append(s1 + str(i))
    #     #
    #     for i in range(1, 630):
    #         self.start_urls.append(s2 + str(i))
        #
        # for i in range(1, 1180):
        #     self.start_urls.append(s3 + str(i))
        # self.hash_penalty_name = {}
        # with open('mid.txt', 'r', encoding='gbk') as f:
        #     lines = f.readlines()
        #     for line in lines:
        #         d = eval(line)
        #         if '表' not in d['penalty_name'] or ('表' in d['penalty_name'] and len(d['agency_name']) > 0) :
        #             self.hash_penalty_name[d['url']] = 1

    # def start_requests(self):
    #     start_urls = [
    #         'http://www.cbirc.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4114&itemUrl=ItemListRightList.html&itemName=%E9%93%B6%E4%BF%9D%E7%9B%91%E5%B1%80%E6%9C%AC%E7%BA%A7&itemsubPId=931&itemsubPName=%E8%A1%8C%E6%94%BF%E5%A4%84%E7%BD%9A#630'
    #         # 'http://www.cbirc.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4115&itemUrl=ItemListRightList.html&itemName=%E9%93%B6%E4%BF%9D%E7%9B%91%E5%88%86%E5%B1%80%E6%9C%AC%E7%BA%A7&itemsubPId=931&itemsubPName=%E8%A1%8C%E6%94%BF%E5%A4%84%E7%BD%9A#1300'
    #         ]
    #     # content = []
    #     # with open('first_line.txt', 'r', encoding='gbk') as f:
    #     #     lines = f.readlines()
    #     #     for line in lines:
    #     #         d = eval(line)
    #     #         content.append(d)
    #     # for d in content:
    #     #     yield scrapy.Request(url=d['url'], callback=self.get_penalty,meta={'penalty' : deepcopy(d), 'is_table' : d['is_table']}, dont_filter=True)
    #     yield scrapy.Request(url=start_urls[0], callback=self.parse, dont_filter=False)


    def parse(self, response):
        s2 = 'http://www.cbirc.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4114&itemUrl=ItemListRightList.html&itemName=%E9%93%B6%E4%BF%9D%E7%9B%91%E5%B1%80%E6%9C%AC%E7%BA%A7&itemsubPId=931&itemsubPName=%E8%A1%8C%E6%94%BF%E5%A4%84%E7%BD%9A#'

        for i in range(1, 630):
            yield scrapy.Request(url=s2 + str(i), callback=self.fun, dont_filter=True)

    def fun(self, response):
        logging.info('in crawl: ' + response.url)
        # 获取该页面下的所有处罚条例标题
        detail_urls = response.xpath(
            '//div[@class="list caidan-right-list"]/div[@class="panels"]/div[@class="panel active"]/div[@class="panel-row ng-scope"]/span[@class="title"]/a[@class="ng-binding"]')
        date_span = response.xpath(
            '//div[@class="list caidan-right-list"]/div[@class="panels"]/div[@class="panel active"]/div[@class="panel-row ng-scope"]/span[@class="date ng-binding"]')
        # page = int(response.url[(response.url.find('#') + 1):])
        # if page >= 2:
        #     page = page - 1
        #     if '#' not in response.url:
        #         redirect_url = response.url + '#' + str(page)
        #     else:
        #         redirect_url = (response.url[0:(response.url.find('#') + 1)] + str(page))
        #     yield scrapy.Request(redirect_url, callback=self.parse, dont_filter=True)

        for i in range(len(detail_urls)):
            url = detail_urls[i]
            date = date_span[i].xpath('.//text()').extract_first()
            penalty = {'penalty_name': url.xpath('.//@title').extract_first(), 'penalty_date': date}
            detail_info_url = 'http://www.cbirc.gov.cn/cn/view/pages/' + url.xpath('.//@href').extract_first()
            for pr in self.provinces:
                if pr in penalty['penalty_name']:
                    penalty['punished_party_province'] = pr
            if 'punished_party_province' not in penalty:
                for key in self.pro_nickname:
                    if key in penalty['penalty_name']:
                        penalty['punished_party_province'] = self.pro_nickname[key]
            if '局' in penalty['penalty_name']:
                idx = penalty['penalty_name'].find('局')
                penalty['agency_name'] = penalty['penalty_name'][:(idx + 1)]
            elif '会' in penalty['penalty_name']:
                idx = penalty['penalty_name'].find('会')
                penalty['agency_name'] = penalty['penalty_name'][:(idx + 1)]

            if '（' in penalty['penalty_name']:
                name = deepcopy(penalty['penalty_name'])
                idx = name.find('（')
                end = name.find('）')
                if end < 0:
                    end = len(name)
                penalty['decision_number'] = name[(idx + 1): end]
            elif '(' in penalty['penalty_name']:
                name = deepcopy(penalty['penalty_name'])
                idx = name.find('(')
                end = name.find(')')
                if end < 0:
                    end = len(name)
                penalty['decision_number'] = name[(idx + 1): end]

            penalty['url'] = detail_info_url
            # 公开表还是决定文书
            is_table = 0
            if '表' not in penalty['penalty_name']:
                is_table = 1
            penalty['is_table'] = is_table
            self.save_result(penalty)
            # yield scrapy.Request(detail_info_url, callback=self.get_penalty, meta={'penalty' : deepcopy(penalty), 'is_table' : is_table}, dont_filter=True)


    #
    # def get_penalty(self, response):
    #     logging.info('crawling ' + response.url)
    #     penalty = response.meta['penalty']
    #     is_table = response.meta['is_table']
    #     if is_table == 0:
    #         # 公开表的处理方式
    #         table = response.xpath('//table[@class="MsoNormalTable"]/tbody/tr')
    #         if len(table) == 0:
    #             table = response.xpath('//table[@class="MsoTableGrid"]/tbody/tr')
    #         punished_man_two_lines = 0
    #         if len(table) >= 8:
    #             punished_man_two_lines = len(table) - 7
    #         table = response.xpath('//table[@class="MsoNormalTable"]/tbody')
    #         if len(table) == 0:
    #             table = response.xpath('//table[@class="MsoTableGrid"]/tbody')
    #
    #         # 决定文书
    #         decision_number = ''
    #         decision_number_blocks = table.xpath('.//tr[1]/td[2]/p/span/text()').extract()
    #         for part in decision_number_blocks:
    #             decision_number = decision_number + part
    #         penalty['decision_number'] = decision_number
    #
    #         # 被罚当事人名称、所处机构，机构类型、省份
    #         punished_party_name = ''
    #         punished_party_agency = ''
    #         punished_party_agency_type = ''
    #         punished_party_province = ''
    #         if punished_man_two_lines == 3:
    #             # 个人姓名， 名称， 法定代表人
    #             blocks = table.xpath('.//tr[2]/td[4]/div/p/span').xpath('string(.)').extract_first()
    #             punished_party_name = blocks
    #
    #             blocks = table.xpath('.//tr[3]/td[2]/div/p/span').xpath('string(.)').extract_first()
    #             punished_party_agency = blocks
    #
    #         elif punished_man_two_lines == 2:
    #             # 个人姓名， 名称， 法定代表人
    #             blocks = table.xpath('.//tr[2]/td[3]/p/span/text()').extract()
    #             for part in blocks:
    #                 punished_party_name = punished_party_name + part
    #
    #             blocks = table.xpath('.//tr[3]/td[3]/p/span/text()').extract()
    #             for part in blocks:
    #                 punished_party_agency = punished_party_agency + part
    #             if len(punished_party_name) == 0:
    #                 punished_party_name = punished_party_agency
    #             punished_party_agency = punished_party_agency + '_'
    #
    #             # 法定代表人
    #             blocks = table.xpath('.//tr[4]/td[3]/p/span/text()').extract()
    #             for part in blocks:
    #                 punished_party_agency = punished_party_agency + part
    #         elif punished_man_two_lines == 1:
    #             # 被罚当事人名称用名称_单位的形式存储
    #             blocks = table.xpath('.//tr[2]/td[4]/p/span/text()').extract()
    #             for part in blocks:
    #                 punished_party_name = punished_party_name + part
    #             punished_party_agency = punished_party_name
    #             punished_party_name = punished_party_name + '_'
    #
    #             # 法定代表人
    #             blocks = table.xpath('.//tr[3]/td[2]/p/span/text()').extract()
    #             for part in blocks:
    #                 punished_party_name = punished_party_name + part
    #         else:
    #             blocks = table.xpath('.//tr[2]/td[2]/p/span/text()').extract()
    #             for part in blocks:
    #                 punished_party_name = punished_party_name + part
    #             punished_party_agency = punished_party_name
    #         penalty['punished_party_name'] = punished_party_name
    #         penalty['punished_party_agency'] = punished_party_agency
    #         penalty['punished_party_agency_type'] = punished_party_agency_type
    #         if 'punished_party_province' not in penalty:
    #             penalty['punished_party_province'] = punished_party_province
    #
    #
    #         # 案由
    #         penalty_cause = ''
    #         tr = 3 + punished_man_two_lines
    #         blocks = table.xpath('.//tr[' + str(tr) + ']/td[2]/p/span/text()').extract()
    #         for part in blocks:
    #             penalty_cause = penalty_cause + part
    #         penalty['punished_cause'] = penalty_cause
    #
    #         # 行政处罚依据， 行政处罚法律名称
    #         penalty_basis = ''
    #         penalty_basis_law = ''
    #         tr = 4 + punished_man_two_lines
    #         blocks = table.xpath('.//tr[' + str(tr) + ']/td[2]/p/span/text()').extract()
    #         for part in blocks:
    #             penalty_basis = penalty_basis + part
    #         '''
    #         根据 《》 去获取处罚依据中法律的内容
    #         '''
    #         basis_copy = deepcopy(penalty_basis)
    #         while '《' in basis_copy:
    #             idx = basis_copy.find('《')
    #             basis_copy = basis_copy[(idx + 1):]
    #             idx = basis_copy.find('》')
    #             if idx < 0:
    #                 penalty_basis_law = penalty_basis_law + basis_copy
    #                 break
    #             else:
    #                 penalty_basis_law = penalty_basis_law + '、' + basis_copy[:idx]
    #             basis_copy = basis_copy[(idx + 1):]
    #         penalty['penalty_basis'] = penalty_basis
    #         penalty['penalty_basis_law'] = penalty_basis_law.strip('、')
    #
    #         # 行政处罚决定、行政处罚金额
    #         penalty_decision = ''
    #         penalty_decision_money = ''
    #         tr = 5 + punished_man_two_lines
    #         blocks = table.xpath('.//tr[' + str(tr) + ']/td[2]/p/span/text()').extract()
    #         for part in blocks:
    #             penalty_decision = penalty_decision + part
    #
    #         '''
    #         根据元和数字获得处罚的金额
    #         '''
    #         decision_copy = deepcopy(penalty_decision)
    #         for idx in range(len(decision_copy)):
    #             if decision_copy[idx] >= '0' and decision_copy[idx] <= '9':
    #                 decision_copy = decision_copy[idx:]
    #                 break
    #         idx = decision_copy.find('元')
    #         if idx < 0:
    #             penalty_decision_money = decision_copy
    #         else:
    #             penalty_decision_money = decision_copy[:(idx+1)]
    #         penalty['penalty_decision'] = penalty_decision
    #         penalty['penalty_decision_money'] = penalty_decision_money
    #
    #         # 行政处罚机关名称
    #         agency_name = ''
    #         tr = 6 + punished_man_two_lines
    #         blocks = table.xpath('.//tr[' + str(tr) + ']/td[2]/p/span/text()').extract()
    #         for part in blocks:
    #             agency_name = agency_name + part
    #         penalty['agency_name'] = agency_name
    #
    #         self.save_result(penalty)
    #     else:
    #         # 决定书的处理方式
    #         content = response.xpath('//div[@id="wenzhang-content"]/div/p')
    #         # 决定文号
    #         if 'decision_number' not in penalty:
    #             content = response.xpath('//div[@id="wenzhang-content"]/div/p[1]/span')
    #             decision_number = ''
    #             if len(content) != 0:
    #                 for span in content:
    #                     font_part = span.xpath('string(.)').extract_first()
    #                     decision_number = decision_number + font_part
    #             if len(content) == 0:
    #                 content = response.xpath('//div[@id="wenzhang-content"]/p[1]/text()').extract()
    #                 for part in content:
    #                     decision_number = decision_number + part
    #             penalty['decision_number'] = decision_number
    #
    #         content = response.xpath('//div[@id="wenzhang-content"]/div/p/span')
    #         penalty_content = ''
    #         for span in content:
    #             font_part = span.xpath('string(.)').extract_first()
    #             penalty_content = penalty_content + font_part
    #
    #         penalty_content = penalty_content.replace(' ', '')
    #
    #         # 处理当事人
    #         for pr in self.provinces:
    #             if pr in penalty_content:
    #                 penalty['punished_party_province'] = pr
    #         party_agency_type = ''
    #         party_name = ''
    #         party_list =[]
    #         if '公司' in penalty_content:
    #             idx = penalty_content.find('公司')
    #             st = penalty_content.rfind('：', 0, idx)
    #             if st >= 0:
    #                 penalty['punished_party_agency'] = penalty_content[st:(idx + 2)]
    #         while '当事人：' in penalty_content or '名称：' in penalty_content or '姓名：' in penalty_content :
    #             party_info = {}
    #             # 被罚当事人名称
    #             if penalty_content.find('当事人：') == -1:
    #                 if penalty_content.find('名称：') == -1:
    #                     idx = penalty_content.find('姓名：')
    #                 else:
    #                     idx = penalty_content.find('名称：')
    #
    #             else:
    #                 idx = penalty_content.find('当事人：')
    #
    #             end_idx = self.find_nearest2party(penalty_content, idx)
    #             if end_idx < 0:
    #                 break
    #             if '当事人：' not in penalty_content:
    #                 party_name = penalty_content[(idx + 3):end_idx]
    #             else:
    #                 party_name = penalty_content[(idx + 4):end_idx]
    #             party_info['punished_party_name'] = party_name
    #             party_list.append(party_info)
    #             penalty_content = penalty_content[(idx + 4):]
    #         party_agency = ''
    #         for p in party_list:
    #             if len(p['punished_party_name']) > len(party_agency):
    #                 party_agency = p['punished_party_name']
    #             if len(party_name) == 0:
    #                 party_name = p['punished_party_name']
    #             elif len(party_name) < len(p['punished_party_name']):
    #                 party_name = p['punished_party_name']
    #         penalty['punished_party_name'] = party_name
    #         if 'punished_party_agency' not in penalty:
    #             penalty['punished_party_agency'] = party_agency
    #
    #         # todo 市 -> 省
    #         if 'punished_party_province' not in penalty:
    #             penalty['punished_party_province'] = ''
    #         # 案由
    #         idx = penalty_content.find('违法行为：')
    #         end = penalty_content.find('综上')
    #         if idx < 0 or end < 0:
    #             idx = penalty_content.find('上述')
    #             if idx > 0:
    #                 penalty['punished_cause'] = penalty_content[: idx]
    #                 penalty_content = penalty_content[idx:]
    #             else:
    #                 penalty['punished_cause'] = ''
    #         else:
    #             penalty['punished_cause'] = penalty_content[(idx + 4): end]
    #             penalty_content = penalty_content[end:]
    #
    #
    #
    #         # 依据 + 依据法律名称
    #         laws = {}
    #         idx = 0
    #         while '《' in penalty_content[idx:]:
    #             law_idx = penalty_content.find('《', idx)
    #             law_end = penalty_content.find('》', law_idx)
    #             if law_idx < 0 or law_end < 0:
    #                 break
    #             if penalty_content[(law_idx + 1): law_end] not in laws:
    #                 laws[penalty_content[(law_idx + 1): law_end]] = 1
    #             idx = law_end
    #         penalty_law_basis = ''
    #         for law in laws:
    #             if len(penalty_law_basis) == 0:
    #                 penalty_law_basis = law
    #             else:
    #                 penalty_law_basis = penalty_law_basis + '_《' + law + '》'
    #         penalty_basis = ''
    #         idx = 0
    #         while '《' in penalty_content[idx:]:
    #             law_idx = penalty_content.find('《', idx)
    #             law_end = penalty_content.find('条', law_idx)
    #             if law_end == -1:
    #                 break
    #             if len(penalty_basis) == 0:
    #                 penalty_basis = penalty_content[law_idx:(law_end + 1)]
    #             else:
    #                 penalty_basis = penalty_basis + ',' + penalty_content[law_idx:(law_end + 1)]
    #             idx = law_end
    #         penalty['penalty_basis'] = penalty_basis
    #         penalty['penalty_basis_law'] = penalty_law_basis
    #
    #         # 行政处罚决定
    #         penalty_decision = ''
    #         idx = 0
    #         while '《' in penalty_content[idx:]:
    #             law_end = penalty_content.find('条', idx)
    #             if law_end == -1:
    #                 law_end = penalty_content.find('》', idx)
    #                 if law_end == -1:
    #                     break
    #             nx = penalty_content.find('《', law_end)
    #             if nx < 0:
    #                 nx = penalty_content.find('。', law_end)
    #                 if nx < 0:
    #                     break
    #             if len(penalty_decision) == 0:
    #                 penalty_decision = penalty_content[law_end:nx]
    #             else:
    #                 penalty_decision = penalty_decision + ',' + penalty_content[law_end:nx]
    #             idx = nx
    #         penalty_decision_money = '0'
    #         if '元' in penalty_decision:
    #             idx = penalty_decision.find('元')
    #             st = idx
    #             while st >= 0 and (penalty_decision[st] < '0' or penalty_decision[st] > '9' ):
    #                 st -= 1
    #             while st >= 0 and (penalty_decision[st] >= '0' and penalty_decision[st] <= '9'):
    #                 st -= 1
    #             if st<0:
    #                 penalty_decision_money = penalty_decision[:(idx + 1)]
    #             else:
    #                 penalty_decision_money = penalty_decision[st:(idx + 1)]
    #         penalty['penalty_decision'] = penalty_decision
    #         penalty['penalty_decision_money'] = penalty_decision_money
    #
    #         if 'agency_name' not in penalty:
    #             penalty['agency_name'] = ''
    #
    #         self.save_result(penalty)
    #
    #
    # def find_nearest2party(self, p, idx):
    #     end_idx = 1000000000000
    #     if p.find('地址', idx) != -1:
    #         end_idx = min(end_idx, p.find('地址', idx))
    #     if p.find('法定代表人', idx) != -1:
    #         end_idx = min(end_idx, p.find('法定代表人', idx))
    #     if p.find('住址', idx) != -1:
    #         end_idx = min(end_idx, p.find('住址', idx))
    #
    #     if p.find('身份证', idx) != -1:
    #         end_idx = min(end_idx, p.find('身份证', idx))
    #     if p.find('职务', idx) != -1:
    #         end_idx = min(end_idx, p.find('职务', idx))
    #     if p.find('住所', idx) != -1:
    #         end_idx = min(end_idx, p.find('住所', idx))
    #     if end_idx == 1000000000000:
    #         return -1
    #     else:
    #         return end_idx

    def save_result(self, penalty):
        # 2304
        with open('first_line.txt', 'a+', encoding='utf-') as f:
            f.write(str(penalty) + '\n')


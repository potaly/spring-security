# bdp-mws

The project of belle data monitor platform
## bdp-mws����˵��
- 1.����������ԭ����bdp-monitor���Ϊbdp-mws��

- 2.git�ֿ��ַ��http://github.com

  

  git������������淶

- 1.��ȡ���ϴ�
git pull
git push 
�мǣ�һ������pull֮���ٽ���push�������п��ܻḲ����������
ǿ���ύ��git push -u origin master -f

- 2.�л���֧
�鿴Զ�̷�֧��git branch -a 
���ش���һ����֧�� git checkout -b dev 
Զ�̷�֧��ȡ�����أ� git checkout -b ���ط�֧�� origin/Զ�̷�֧�� 

- 3.�ύһ�δ�������
git add /xx/xx/*   �ɵ�������
git commit -m "Ӱ��ϵͳ����OSS��ز����ӿڴ���"  �ύ�����ؿ⣬������˵��
git pull  �� git pull origin dev
git push �� git push origin dev

- 4.�����������ֳ�
git stash
git stash list
git stash pop stash@{num}��num ������Ҫ�ָ��Ĺ����ֳ��ı�š�
git stash clear 
git stash apply stash@{num}���� ���˲���stash����ɾ����������git stash pop ��ȫһ����